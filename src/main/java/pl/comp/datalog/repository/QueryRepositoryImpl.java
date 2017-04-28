package pl.comp.datalog.repository;

import org.deri.iris.EvaluationException;
import org.deri.iris.compiler.ParserException;
import org.springframework.stereotype.Service;
import pl.comp.datalog.dto.QueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.deri.iris.Configuration;
import org.deri.iris.KnowledgeBase;
import org.deri.iris.api.IKnowledgeBase;
import org.deri.iris.api.basics.IPredicate;
import org.deri.iris.api.basics.IQuery;
import org.deri.iris.api.basics.IRule;
import org.deri.iris.api.terms.IVariable;
import org.deri.iris.compiler.Parser;
import org.deri.iris.optimisations.magicsets.MagicSets;
import org.deri.iris.storage.IRelation;
import pl.comp.datalog.model.Fact;
import pl.comp.datalog.model.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Damian Ratajczak
 */
@Service
public class QueryRepositoryImpl implements QueryRepository {

    @Autowired
    private FactRepository factRepository;
    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public QueryDTO resolve(QueryDTO query) {

        Parser parser = new Parser();
        Map<IPredicate, IRelation> factMap = new HashMap<>();


         List<Fact> L = factRepository.findAll();
         String s = L.stream().map(e -> e.getValue().toString()).reduce("", String::concat);
        List<Rule> RL = ruleRepository.findAll();

        String rs = RL.stream().map(e -> e.getValue().toString()).reduce("", String::concat);

        try {
            parser.parse(s);
        } catch (ParserException e) {
            e.printStackTrace();
        }

        // Retrieve the facts and put all of them in factMap
        factMap.putAll(parser.getFacts());

        try {
            parser.parse(rs);
        } catch (ParserException e) {
            e.printStackTrace();
        }
        List<IRule> rules = parser.getRules();

        try {
            parser.parse(query.getValue());
        } catch (ParserException e) {
            e.printStackTrace();
        }
        List<IQuery> queries = parser.getQueries();
        // Create a default configuration.
        Configuration configuration = new Configuration();

        // Enable Magic Sets together with rule filtering.
        configuration.programOptmimisers.add(new MagicSets());

        // Create the knowledge base.
        IKnowledgeBase knowledgeBase = null;
        String res = "";
        try {
            knowledgeBase = new KnowledgeBase(factMap, rules, configuration);
            for (IQuery Inquery : queries) {

                List<IVariable> variableBindings = new ArrayList<>();
                IRelation relation = knowledgeBase.execute(Inquery, variableBindings);

                // Output the variable bindings.
                s += "\n" + query.toString() + "\n" + variableBindings;

                // Output each tuple in the relation, where the term at position i
                // corresponds to the variable at position i in the variable
                // bindings list.
                for (int i = 0; i < relation.size(); i++) {
                    res+= relation.get(i);
                }
            }

        } catch (EvaluationException e) {
            e.printStackTrace();
        }


        query.setResult(res);
        return query;
    }
}
