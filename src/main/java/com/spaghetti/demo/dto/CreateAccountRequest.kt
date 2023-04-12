package com.spaghetti.demo.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CreateAccountRequest(
        @field:NotBlank(message = "CustomerID must not be empty")
        val customerId:String,
        @field:Min(0, message = "Initial Credit must be greater than 0")
        val initialCredit:BigDecimal
)
