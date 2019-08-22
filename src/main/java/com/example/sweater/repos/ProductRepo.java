package com.example.sweater.repos;


import com.example.sweater.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    List<Product> findAllByOrderByIdDesc();
    List<Product> findFirst50ByOrderByIdDesc();
    List<Product> findByBarcode(String barcode);
    List<Product> findAllByOrderByProductNameAsc();
    List<Product> findAllByOrderByProductNameDesc();
    List<Product> findAllByOrderByGenderAsc();
    Product findFirst1ByOrderByIdDesc ();
    Product findFirst1ByAuthorOrderByIdDesc (String author);

    List<Product> findByBarcodeOrderByIdAsc(String filter);
    List<Product> findByProductNameOrderByIdAsc(String filter);
    List<Product> findByGenderOrderByIdAsc(String filter);
    List<Product> findByTrademarkOrderByIdAsc(String filter);
    List<Product> findBySeasonOrderByIdAsc(String filter);
    List<Product> findByBoxNumberOrderByIdAsc(String filter);

    List<Product> findByIdOrderByIdAsc(Long id);
    List<Product> findById(Long id);
    List<Product> findByisDistrib(Integer id);



}

