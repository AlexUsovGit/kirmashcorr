package com.example.sweater.repos;


import com.example.sweater.domain.Receipt;
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

    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where rn.author  = :author and cast(r.sale_date as date) =  Cast(:today as date) " +
            "order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllByAuthorOrderBySaleDateDesc(String author, String today);



    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where rn.author  = :author and cast(r.sale_date as date) >=  Cast(:dateFrom as date) " +
            "and cast(r.sale_date as date) >=  Cast(:dateTo as date) " +
            "order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllByAuthor2OrderBySaleDateDesc(String author, String dateFrom, String dateTo);


    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where Cast(r.sale_date as date) =  Cast(:today as date) " +
            "order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllOrderBySaleDateDesc(String today);

    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where Cast(r.sale_date as date) =  Cast(:today as date) and store_name  = :department " +
            "order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllBySaleDateAndDepartmentOrderBySaleDateDesc(String today, String department);


    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where Cast(r.sale_date as date) >= Cast(:dateFrom as date) and Cast(r.sale_date as date) <=  Cast(:dateTo as date)" +
            " and store_name  like  %:department% " +
            " order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllBySaleDate2AndDepartmentOrderBySaleDateDesc(String dateFrom, String dateTo, String department);


    @Query(value = "SELECT Cast(cost as decimal ) from  receipt", nativeQuery = true)
    List<Double> findAllCost();
}

