package com.spaghetti.demo.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class CustomerAccountDto(
        val id:String?,
        val creationDate: LocalDateTime?,
        val balance: BigDecimal?,
        val transactions: Set<TransactionDto>?
)
