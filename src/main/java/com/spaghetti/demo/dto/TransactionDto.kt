package com.spaghetti.demo.dto

import com.spaghetti.demo.model.Account
import com.spaghetti.demo.model.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
        val id: String?,
        val amount: BigDecimal?,
        val transactionType: TransactionType?,
        val transactionDate: LocalDateTime? = LocalDateTime.now()
)
