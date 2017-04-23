package pl.comp.datalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.comp.datalog.model.Rule;
import pl.comp.datalog.repository.QueryRepository;
import pl.comp.datalog.repository.RuleRepository;

import java.util.List;

/**
 * Created by Damian Ratajczak
 */
@Controller
public class QueryController {

    @Autowired
    private QueryRepository queryRepository;

    @ResponseBody
    @RequestMapping(path = "/api/query/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getQueryResult(@PathVariable String name) {
        return queryRepository.resolve(name);
    }


}
