package com.example.productservice.product

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.time.Instant

@Service
class ProductService(private val repository: ProductRepository) {

    @Transactional(readOnly = true)
    fun findAll(): List<ProductResponse> =
        repository.findAll(Sort.by(Sort.Direction.DESC, "id")).map { it.toResponse() }

    @Transactional(readOnly = true)
    fun findById(id: Long): ProductResponse =
        repository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Product $id was not found")
        }.toResponse()

    @Transactional
    fun create(request: CreateProductRequest): ProductResponse {
        val product = Product(
            name = request.name.trim(),
            description = request.description?.trim()?.ifBlank { null },
            price = request.price,
            stock = request.stock
        )
        return repository.save(product).toResponse()
    }

    @Transactional
    fun update(id: Long, request: UpdateProductRequest): ProductResponse {
        val product = repository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Product $id was not found")
        }
        product.name = request.name.trim()
        product.description = request.description?.trim()?.ifBlank { null }
        product.price = request.price
        product.stock = request.stock
        product.updatedAt = Instant.now()
        return repository.save(product).toResponse()
    }

    @Transactional
    fun delete(id: Long) {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product $id was not found")
        }
        repository.deleteById(id)
    }
}
