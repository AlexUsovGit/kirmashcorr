package com.example.sweater.repos.basedictionaryrepos;
import com.example.sweater.domain.basedictionary.Composition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompositionRepo extends CrudRepository<Composition, Long> {
    List<Composition> findByLabel(String barcode);


    void deleteById(Long id);
}
