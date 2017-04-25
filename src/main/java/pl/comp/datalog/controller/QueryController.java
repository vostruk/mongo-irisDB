package pl.comp.datalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.comp.datalog.dto.QueryDTO;
import pl.comp.datalog.repository.QueryRepository;

/**
 * Created by Damian Ratajczak
 */
@Controller
public class QueryController {

    @Autowired
    private QueryRepository queryRepository;

    @ResponseBody
    @RequestMapping(path = "/api/query", method = RequestMethod.POST, produces = "application/json")
    public QueryDTO getQueryResult(@RequestBody QueryDTO query) {
        return queryRepository.resolve(query);
    }

}
