package com.example.sweater.repos;


import com.example.sweater.domain.Product;
import com.example.sweater.domain.Receipt;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReceiptRepo extends CrudRepository<Receipt, Long> {

    List<Receipt> findAllByOrderByIdDesc();

    List<Receipt> findAllByReceiptNumberOrderBySaleDateDesc(String receiptNumber);

      @Query(value = "Select * from product where UPPER(product_name) like %:filter% " +
            " or UPPER(article) like %:filter%  or UPPER(barcode) like %:filter%" +
            " or UPPER(gender) like %:filter%" +
            " or UPPER(season) like %:filter%" +
            " or UPPER(box_number) like %:filter%" +
            " or UPPER(author) like %:filter%" +
            " or UPPER(date_arrive) like %:filter%" +
            " ORDER by Id DESC", nativeQuery = true)
    List<Receipt> findByFilterOrderByIdAsc(String filter);

      Receipt findFirstById(Long id);





}

