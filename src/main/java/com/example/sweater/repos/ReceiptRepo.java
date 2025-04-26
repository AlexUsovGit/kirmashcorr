package com.example.sweater.repos;


import com.example.sweater.domain.Receipt;
import com.example.sweater.dto.CommonInfoDTO;
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

    @Query(value = "SELECT " +
            "    r.id," +
            "    r.product_name," +
            "    r.receipt_number," +
            "    r.barcode," +
            "    r.retail_price," +
            "    r.sale_date," +
            "    r.count," +
            "    r.discount," +
            "    r.cost," +
            "    r.status," +
            "    r.gender," +
            "    r.store_name," +
            "    r.author," +
            "    r.date_arrive," +
            "    r.box_number" +
            " FROM receipt r " +
            " LEFT JOIN receipt_number rn ON CAST(r.receipt_number AS int8) = rn.id " +
            " WHERE rn.author = :author " +
            "  AND CAST(rn.date AS date) = CAST(:today AS date) " +
            " ORDER BY r.sale_date DESC " +
            " LIMIT 500", nativeQuery = true)
    List<Receipt> findAllByAuthorOrderBySaleDateDesc(String author, String today);


    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where rn.author  = :author and cast(r.sale_date as date) >=  Cast(:dateFrom as date) " +
            "and cast(r.sale_date as date) >=  Cast(:dateTo as date) " +
            "order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllByAuthor2OrderBySaleDateDesc(String author, String dateFrom, String dateTo);
//select * from receipt where sale_date >= to_date('10.07.2020', 'dd.MM.yyyy') and sale_date <= to_date('11.07.2020', 'dd.MM.yyyy');

    @Query(value = "SELECT " +
            "    r.id," +
            "    r.product_name," +
            "    r.receipt_number," +
            "    r.barcode," +
            "    r.retail_price," +
            "    r.sale_date," +
            "    r.count," +
            "    r.discount," +
            "    r.cost," +
            "    r.status," +
            "    r.gender," +
            "    r.store_name," +
            "    r.author," +
            "    r.date_arrive," +
            "    r.box_number" +
            " from  receipt  r " +
            " where cast(r.sale_date as date) >=  Cast(:dateFrom as date) " +
            " and cast(r.sale_date as date) <=  Cast(:dateTo as date) " +
            " order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllBySaleDate(String dateFrom, String dateTo);

    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where Cast(r.sale_date as date) =  Cast(:today as date) " +
            "order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllOrderBySaleDateDesc(String today);

    @Query(value = "SELECT r.store_name as store, sum(cast(r.count as float8)) as counting, sum(cast(r.cost as float8)) as summa from  receipt  r  " +
            "         left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id  " +
            "         where Cast(r.sale_date as date) =  Cast(:today as date)  " +
            "         GROUP BY r.store_name " +
            "         order by r.store_name asc", nativeQuery = true)
    List<CommonInfoDTO> findAllReceiptBySaleDateDesc(String today);

    @Query(value = "SELECT * from  receipt  r " +
            "left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            "where Cast(r.sale_date as date) =  Cast(:today as date) and store_name  = :department " +
            "order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllBySaleDateAndDepartmentOrderBySaleDateDesc(String today, String department);


    @Query(value = "SELECT " +
            "    r.id," +
            "    r.product_name," +
            "    r.receipt_number," +
            "    r.barcode," +
            "    r.retail_price," +
            "    r.sale_date," +
            "    r.count," +
            "    r.discount," +
            "    r.cost," +
            "    r.status," +
            "    r.gender," +
            "    r.store_name," +
            "    r.author," +
            "    r.date_arrive," +
            "    r.box_number " +
            " from  receipt  r " +
            " left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id " +
            " where Cast(r.sale_date as date) >= Cast(:dateFrom as date) and Cast(r.sale_date as date) <=  Cast(:dateTo as date)" +
            " and store_name  like  %:department% " +
            " order by r.sale_date Desc", nativeQuery = true)
    List<Receipt> findAllBySaleDate2AndDepartmentOrderBySaleDateDesc(String dateFrom, String dateTo, String department);


}

