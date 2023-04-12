package com.spaghetti.demo.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Transaction(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?,

        val amount: BigDecimal?,
        val transactionType: TransactionType? = TransactionType.INITIAL,
        val transactionDate: LocalDateTime?,

        @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [CascadeType.ALL])
        @JoinColumn(name = "account_id", nullable = false)
        val account: Account?
) {
    constructor(amount: BigDecimal, transactionDate: LocalDateTime, account: Account?) : this(
            id = "",
            amount = amount,
            transactionType = TransactionType.INITIAL,
            transactionDate = transactionDate,
            account = account
    )

    constructor() : this("", null, null, null, null)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transaction

        if (id != other.id) return false
        if (amount != other.amount) return false
        if (transactionType != other.transactionType) return false
        if (transactionDate != other.transactionDate) return false
        if (account != other.account) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (amount?.hashCode() ?: 0)
        result = 31 * result + (transactionType?.hashCode() ?: 0)
        result = 31 * result + (transactionDate?.hashCode() ?: 0)

        return result
    }


}

enum class TransactionType {
    INITIAL, PAYMENT
}
