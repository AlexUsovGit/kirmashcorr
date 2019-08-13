package com.example.sweater.repos;

import com.example.sweater.domain.DocumentPdf;
import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentpdfRepo extends CrudRepository <DocumentPdf, Integer>{
    List<DocumentPdf> findByBarcode(String barcode);
}
