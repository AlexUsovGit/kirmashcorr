package com.example.sweater.repos;

import com.example.sweater.domain.ReceiptNumber;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptNumberRepo extends CrudRepository<ReceiptNumber, Long> {

    ReceiptNumber findFirst1ByOrderByIdDesc();


}
