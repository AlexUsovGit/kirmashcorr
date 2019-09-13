package com.example.sweater.repos;

import com.example.sweater.domain.ColumnNames;
import com.example.sweater.domain.ColumnNames2;
import com.example.sweater.domain.TableNames;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColumnNamesRepo extends CrudRepository<ColumnNames2, Long> {


    @Query(value = "SELECT column_name FROM information_schema.columns where table_schema = 'public'", nativeQuery = true)
    List<ColumnNames2> findAllInfoColumn();

    @Query(value = "SELECT column_name FROM information_schema.columns where table_schema = 'public'  and table_name = :tablename", nativeQuery = true)
    List<ColumnNames2> findAllInfoColumnbyTableName(String tablename);


}
