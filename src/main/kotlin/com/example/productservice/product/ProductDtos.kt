package com.example.productservice.product

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.Instant

data class CreateProductRequest(
    @field:NotBlank
    @field:Size(max = 120)
    val name: String,

    @field:Size(max = 1000)
    val description: String? = null,

    @field:DecimalMin(value = "0.00", inclusive = true)
    val price: BigDecimal,

    @field:PositiveOrZero
    val stock: Int
)

data class UpdateProductRequest(
    @field:NotBlank
    @field:Size(max = 120)
    val name: String,

    @field:Size(max = 1000)
    val description: String? = null,

    @field:DecimalMin(value = "0.00", inclusive = true)
    val price: BigDecimal,

    @field:PositiveOrZero
    val stock: Int
)

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val stock: Int,
    val createdAt: Instant,
    val updatedAt: Instant
)

fun Product.toResponse() = ProductResponse(
    id = requireNotNull(id),
    name = name,
    description = description,
    price = price,
    stock = stock,
    createdAt = createdAt,
    updatedAt = updatedAt
)
