package com.example.sweater.repos.basedictionaryrepos;
import com.example.sweater.domain.basedictionary.Composition;
import com.example.sweater.domain.basedictionary.ProductName;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductNameRepo extends CrudRepository<ProductName, Long> {
    List<ProductName> findByLabel(String barcode);


    void deleteById(Long id);
}
