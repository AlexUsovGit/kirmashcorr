package com.example.sweater.repos;


import com.example.sweater.dto.InfoDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InfoDTORepo extends CrudRepository<InfoDTO, Long> {

    List<InfoDTO> findAll();


}

