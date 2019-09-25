package com.example.sweater.repos;

import com.example.sweater.domain.Receipt;
import com.example.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository <User, Long>{
    User findByUsername(String username);

    @Query(value = "SELECT DISTINCT store_name from  usr  " +
            " order by store_name Asc", nativeQuery = true)
    List<String> findAllDepartmentOrderByNameAsc();


}
