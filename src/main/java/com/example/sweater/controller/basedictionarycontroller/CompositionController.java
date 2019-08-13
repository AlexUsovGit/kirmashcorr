package com.example.sweater.controller.basedictionarycontroller;

import com.example.sweater.domain.basedictionary.Composition;
import com.example.sweater.repos.basedictionaryrepos.CompositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class CompositionController {
    @Autowired
    private CompositionRepo compositionRepo;

    @GetMapping("/composition")
    public String composition(Map<String, Object> model) {
        Iterable<Composition> compositions = compositionRepo.findAll();
        model.put("compositions",compositions);

        return "composition";
    }

    @PostMapping("/composition")
    public String add( String label, Map<String, Object> model)  {
        Composition composition = new Composition(label,"autor","active",new Date(System.currentTimeMillis()));
        compositionRepo.save(composition);

        Iterable<Composition> compositions = compositionRepo.findAll();
        model.put("compositions",compositions);

        return "composition";
    }


    @PostMapping("filtercomposition")
    public String filter (@RequestParam String filter, Map<String, Object> model){
        Iterable<Composition> compositions;

        if(filter!=null && !filter.isEmpty()){
            compositions = compositionRepo.findByLabel(filter);
        }else {
            compositions = compositionRepo.findAll();
        }
        model.put("compositions", compositions);
        return "composition";
    }

    @PostMapping("deletecomposition")
    public String delete (@RequestParam String filter, Map<String, Object> model){
        Iterable<Composition> compositions;

        if(filter!=null && !filter.isEmpty()){
            compositionRepo.deleteById(Long.valueOf(filter));
        }else {

        }
        compositions = compositionRepo.findAll();
        model.put("compositions", compositions);
        return "composition";
    }

}