package com.spaghetti.demo.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountDto(
        val id: String?,
        val creationDate: LocalDateTime? = LocalDateTime.now(),
        val balance: BigDecimal? = BigDecimal.ZERO,
        val customer: AccountCustomerDto?,
        val transactions: Set<TransactionDto>?
)
