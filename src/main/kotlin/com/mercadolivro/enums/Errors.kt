package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML1001("ML-1001", "Book {%s} not exists"),
    ML1002("ML-1002", "Cannot update book with status {%s}"),
    ML2001("ML-2001", "Customer {%s} not exists"),

    ML0001("ML-0001", "Invalid Request")
}