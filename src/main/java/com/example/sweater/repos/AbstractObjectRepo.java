package com.example.sweater.repos;

import com.example.sweater.domain.AbstractObject;
import com.example.sweater.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbstractObjectRepo extends CrudRepository<AbstractObject, Long> {

    @Query(value = "Select concat('[',article,'][',barcode,'][',  code,']') as info from ?1", nativeQuery = true)

    List<AbstractObject> findAll(String mytable);

}
