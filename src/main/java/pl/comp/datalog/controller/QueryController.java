package pl.comp.datalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.comp.datalog.model.Query;
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
    @RequestMapping(path = "/api/query", method = RequestMethod.POST, produces = "application/json")
    public Query getQueryResult(@RequestBody Query query) {
        return queryRepository.resolve(query);
    }

}
