package pl.comp.datalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.comp.datalog.model.Fact;
import pl.comp.datalog.repository.FactRepository;

import java.util.List;

/**
 * Created by Damian Ratajczak
 */
@Controller
public class FactController {

    @Autowired
    private FactRepository factRepository;

    @ResponseBody
    @RequestMapping(path = "/api/facts", method = RequestMethod.POST, produces = "application/json")
    public Fact addFact(@RequestBody Fact fact) {
        return factRepository.insert(fact);
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts", method = RequestMethod.PUT,  produces = "application/json")
    public Fact updateFact(@RequestBody Fact fact) {
        return factRepository.save(fact);
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts/{id}", method = RequestMethod.DELETE)
    public void removeFact(@PathVariable String id) {
        factRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts", method = RequestMethod.GET, produces = "application/json")
    public List<Fact> getFacts() {
        return factRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts/{id}", method = RequestMethod.GET, produces = "application/json")
    public Fact getFactById(@PathVariable String id) {
        return factRepository.findById(id);
    }


}
