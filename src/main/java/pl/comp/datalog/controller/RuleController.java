package pl.comp.datalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.comp.datalog.dto.FactDTO;
import pl.comp.datalog.dto.RuleDTO;
import pl.comp.datalog.model.Fact;
import pl.comp.datalog.model.Rule;
import pl.comp.datalog.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian Ratajczak
 */
@Controller
public class RuleController {

    @Autowired
    private RuleRepository ruleRepository;

    @ResponseBody
    @RequestMapping(path = "/api/rules", method = RequestMethod.POST, produces = "application/json")
    public RuleDTO addRule(@RequestBody RuleDTO rule) {
        return new RuleDTO(ruleRepository.insert(new Rule(rule)));
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules", method = RequestMethod.PUT,  produces = "application/json")
    public RuleDTO updateRule(@RequestBody RuleDTO rule) {
        return new RuleDTO(ruleRepository.save(new Rule(rule)));
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules/{id}", method = RequestMethod.DELETE)
    public void removeRule(@PathVariable String id) {
        ruleRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules", method = RequestMethod.GET, produces = "application/json")
    public List<RuleDTO> getRules() {
        List<RuleDTO> results = new ArrayList<>();
        for (Rule rule : ruleRepository.findAll()) {
            results.add(new RuleDTO(rule));
        }
        return results;
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules/{id}", method = RequestMethod.GET, produces = "application/json")
    public RuleDTO getRuleById(@PathVariable String id) {
        return new RuleDTO(ruleRepository.findById(id));
    }


}
