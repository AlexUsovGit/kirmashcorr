package com.example.sweater.repos;


import com.example.sweater.domain.Product;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    List<Product> findAllByOrderByIdDesc();
    List<Product> findAllByAuthorOrderByIdDesc(String author);
    List<Product> findFirst50ByOrderByIdDesc();
    List<Product> findFirst50ByAuthorOrderByIdDesc(String author);
    List<Product> findByBarcode(String barcode);
    Sort sort  = new Sort(Sort.Direction.ASC, "Name");

    List<Product> findAllByOrderByProductNameAsc();
    List<Product> findAllByOrderByProductNameDesc();
    List<Product> findAllByOrderByGenderAsc();
    Product findFirst1ByOrderByIdDesc ();
    Product findFirst1ByBarcode(String barcode);
    Product findFirst1ByAuthorOrderByIdDesc (String author);

    List<Product> findByBarcodeOrderByIdAsc(String filter);
    List<Product> findByAuthorOrderByIdAsc(String filter);
    @Query(value = "Select * from product where UPPER(product_name) like %:filter% " +
            " or UPPER(article) like %:filter%  or UPPER(barcode) like %:filter%" +
            " or UPPER(gender) like %:filter%" +
            " or UPPER(season) like %:filter%" +
            " or UPPER(box_number) like %:filter%" +
            " or UPPER(author) like %:filter%" +
            " or UPPER(date_arrive) like %:filter%" +
            " ORDER by Id DESC", nativeQuery = true)
    List<Product> findByFilterOrderByIdAsc(String filter);
    List<Product> findByGenderOrderByIdAsc(String filter);
    List<Product> findByTrademarkOrderByIdAsc(String filter);
    List<Product> findBySeasonOrderByIdAsc(String filter);
    List<Product> findByBoxNumberOrderByIdAsc(String filter);

    List<Product> findByIdOrderByIdAsc(Long id);
    List<Product> findById(Long id);
    List<Product> findByisDistrib(Integer id);



}

