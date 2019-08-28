package com.example.sweater.repos.basedictionaryrepos;
import com.example.sweater.domain.basedictionary.Composition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompositionRepo extends CrudRepository<Composition, Long> {

    List<Composition> findByLabel(String barcode);
    List<Composition> findAllByOrderByLabelAsc();
    Composition findById(long id);



}
