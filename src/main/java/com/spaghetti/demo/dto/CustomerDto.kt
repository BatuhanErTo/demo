package com.spaghetti.demo.dto

import com.spaghetti.demo.model.Account

data class CustomerDto(
        val id: String,
        val name: String,
        val surname: String,
        val accounts: Set<CustomerAccountDto>?
)
