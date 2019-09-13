package com.example.sweater.repos;

import com.example.sweater.domain.ColumnNames;
import com.example.sweater.domain.TableNames;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableNamesRepo extends CrudRepository<TableNames, Long> {

    @Query(value = "SELECT table_name FROM information_schema.tables where table_schema = 'public' order by table_name ASC",
            nativeQuery = true)
    List<TableNames> findAllInfo();




}
