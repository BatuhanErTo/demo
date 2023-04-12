package com.spaghetti.demo.dto.converter;

import com.spaghetti.demo.dto.TransactionDto;
import com.spaghetti.demo.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    public TransactionDto convertToTransactionDto(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTransactionDate());
    }
}
