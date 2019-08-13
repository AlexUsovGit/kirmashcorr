package com.example.sweater.repos;


import com.example.sweater.domain.DistributionDoc;
import com.example.sweater.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistributionDocRepo extends CrudRepository<DistributionDoc, Integer> {

    List<DistributionDoc> findAll();
    List<DistributionDoc> findAllByDocNumber(Integer docNumber);

    DistributionDoc findFirstByOrderByDocNumberDesc();



}
