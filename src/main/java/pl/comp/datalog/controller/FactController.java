package pl.comp.datalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.comp.datalog.dto.FactDTO;
import pl.comp.datalog.dto.RuleDTO;
import pl.comp.datalog.model.Fact;
import pl.comp.datalog.repository.FactRepository;

import java.util.ArrayList;
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
    public FactDTO addFact(@RequestBody FactDTO fact) {
        return new FactDTO(factRepository.insert(new Fact(fact)));
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts", method = RequestMethod.PUT,  produces = "application/json")
    public FactDTO updateFact(@RequestBody FactDTO fact) {
        return new FactDTO(factRepository.save(new Fact(fact)));
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts/{id}", method = RequestMethod.DELETE)
    public void removeFact(@PathVariable String id) {
        factRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts", method = RequestMethod.GET, produces = "application/json")
    public List<FactDTO> getFacts() {
        List<FactDTO> results = new ArrayList<>();
        for (Fact fact : factRepository.findAll()) {
            results.add(new FactDTO(fact));
        }
        return results;
    }

    @ResponseBody
    @RequestMapping(path = "/api/facts/{id}", method = RequestMethod.GET, produces = "application/json")
    public FactDTO getFactById(@PathVariable String id) {
        return new FactDTO(factRepository.findById(id));
    }


}
