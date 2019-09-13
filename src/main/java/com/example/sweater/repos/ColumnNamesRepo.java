package com.example.sweater.repos;

import com.example.sweater.domain.ColumnNames;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColumnNamesRepo extends CrudRepository<ColumnNames, Long> {


    @Query(value = "SELECT column_name FROM information_schema.columns where table_schema = 'public'", nativeQuery = true)
    List<ColumnNames> findAllInfoColumn();

    @Query(value = "SELECT column_name FROM information_schema.columns where table_schema = 'public'  and table_name = :tablename", nativeQuery = true)
    List<ColumnNames> findAllInfoColumnbyTableName(String tablename);


}
