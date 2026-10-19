package com.example.productservice.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun existsByNameIgnoreCase(name: String): Boolean
}
