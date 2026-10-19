package com.example.productservice.product

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 120)
    var name: String = "",

    @Column(length = 1000)
    var description: String? = null,

    @Column(nullable = false, precision = 12, scale = 2)
    var price: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    var stock: Int = 0,

    @Column(nullable = false, updatable = false)
    var createdAt: Instant = Instant.now(),

    @Column(nullable = false)
    var updatedAt: Instant = Instant.now()
)
