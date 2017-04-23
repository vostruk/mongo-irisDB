package pl.comp.datalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.comp.datalog.model.Rule;
import pl.comp.datalog.repository.RuleRepository;

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
    public Rule addRule(@RequestBody Rule rule) {
        return ruleRepository.insert(rule);
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules", method = RequestMethod.PUT,  produces = "application/json")
    public Rule updateRule(@RequestBody Rule rule) {
        return ruleRepository.save(rule);
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules/{id}", method = RequestMethod.DELETE)
    public void removeRule(@PathVariable String id) {
        ruleRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules", method = RequestMethod.GET, produces = "application/json")
    public List<Rule> getRules() {
        return ruleRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(path = "/api/rules/{id}", method = RequestMethod.GET, produces = "application/json")
    public Rule getRuleById(@PathVariable String id) {
        return ruleRepository.findById(id);
    }


}
