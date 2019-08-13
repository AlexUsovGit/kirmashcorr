package com.example.sweater.repos;


import com.example.sweater.domain.InfoClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface InfoClassRepo extends CrudRepository<InfoClass, Long> {

    List<InfoClass>  findAll();
}
