package pl.comp.datalog.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
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
import pl.comp.datalog.dto.RuleDTO;
import pl.comp.datalog.model.Fact;
import pl.comp.datalog.model.Rule;

import java.util.*;

/**
 * Created by Damian Ratajczak
 */
@Service
public class QueryRepositoryImpl implements QueryRepository {

    @Autowired
    private FactRepository factRepository;
    @Autowired
    private RuleRepository ruleRepository;

    //DONE
    public List<String> findWithRegexAllNeededStrings(Set<String> listOfValues)
    {
        //przypisac wszystkie facty tutaj
        List<String> facts = new ArrayList<>();
        for (Fact fact : factRepository.findAll()) {
            facts.add(fact.getValue());
        }

        List<String> results = new ArrayList<>();
        for (String val : listOfValues)
        {
            if(val.contains("("))
            {
                results.add(val);
            }
            else
            {
                for (String fact : facts)
                {
                    if(fact.split("\\(")[0].contains(val))
                    {
                        results.add(fact);
                    }
                }

            }
        }

        return results;
    }

    //DONE
    public Set<String> getUniqueValuesFromList(List<String> list)
    {
        Set<String> uniqueList = new HashSet<String>(list);
        return uniqueList;
    }

    //DONE
    public Set<String> getUniqueFactsName()
    {
        List<String> facts = new ArrayList<>();
        for (Fact fact : factRepository.findAll()) {
            facts.add(fact.getValue());
        }

        List<String> results = new ArrayList<>();

        for (String fact : facts)
        {
            results.add(fact.split("\\(")[0]);
        }

        Set<String> uniqueFactNames = new HashSet<String>(results);
        return uniqueFactNames;
    }
    //
    //musi zwracać liste stringów, kazdy string to rule lub fact, ale po prostu nazwa, bez nawiasow
    public List<String> parseQuery(QueryDTO query, Set<String> facts)
    {
        //dla kilku wartosci w wuery podzielic po nawiasie zamykajacym rownież!
        //wiec zakladam że mam tutaj
        List<String> rules= new ArrayList<>();
        for (Rule rule : ruleRepository.findAll()) {
            rules.add(rule.getValue());
        }

        List<String> result = new ArrayList<>();
        //String[] queries = query.getValue().substring(3).split("\\)");
        String rawQuery = query.getValue().split("\\?-")[1];
        String[] queries = rawQuery.trim().split("\\)");
        boolean moreThanOne = false;
        for(String q : queries)
        {
            if(q.length() > 1)
            {
                //q += ").";
                if(moreThanOne)
                {
                    q = q.substring(2);
                }

                String value = q.split("\\(")[0].trim();

                //a moze trzeba to i tak i tak-> jesli moze cos byc i regulą i faktem
                if(facts.contains(value))
                {
                    result.add(value);
                }
                result.addAll(parseRule(value, facts, rules));
            }
            moreThanOne = true;
        }

        return result;
    }

    //musi zwracać liste stringów, kazdy string to rule lub fact, ale po prostu nazwa, bez nawiasow
    public List<String> parseRule(String rule , Set<String> facts, List<String> rules)
    {
        List<String> result = new ArrayList<>();

        for (String r : rules)
        {
            if(rule.equals(r.split("\\(")[0]))
            {
                result.add(r);

                String value = r.split(":-")[1];

                String[] parts = value.split("\\)");
                boolean moreThanOne = false;
                for(String q : parts)
                {
                    if(q.length() > 1)
                    {
                        //q += ").";
                        if(moreThanOne)
                        {
                            q = q.substring(2);
                        }

                        String onePart = q.split("\\(")[0].trim();

                        if(facts.contains(onePart))
                        {
                            result.add(onePart);
                        }

                        result.addAll(parseRule(onePart, facts, rules));

                    }
                    moreThanOne = true;
                }
            }
        }


        return result;
    }


    @Override
    public QueryDTO resolve(QueryDTO query) {

        Parser parser = new Parser();
        Map<IPredicate, IRelation> factMap = new HashMap<>();
        List<String> factsAndRulesNamesList = parseQuery(query, getUniqueFactsName());
        List<String> allNeededToIris = findWithRegexAllNeededStrings(getUniqueValuesFromList(factsAndRulesNamesList));
        String ALLs  = String.join(" ", allNeededToIris);
        try {
            parser.parse(ALLs);
        } catch (ParserException e) {
            e.printStackTrace();
        }
        List<IRule> rules = parser.getRules();
        try {
            parser.parse(ALLs);
            factMap.putAll(parser.getFacts());
            parser.parse(query.getValue());
        } catch (ParserException e) {
            e.printStackTrace();
        }
        List<IQuery> queries = parser.getQueries();
        Configuration configuration = new Configuration();
        configuration.programOptmimisers.add(new MagicSets());
        IKnowledgeBase knowledgeBase = null;

        String res = "";
        try {
            knowledgeBase = new KnowledgeBase(factMap, rules, configuration);
            for (IQuery Inquery : queries) {

                List<IVariable> variableBindings = new ArrayList<>();
                IRelation relation = knowledgeBase.execute(Inquery, variableBindings);

                // Output the variable bindings.
                //s += "\n" + query.toString() + "\n" + variableBindings;

                // Output each tuple in the relation, where the term at position i
                // corresponds to the variable at position i in the variable bindings list.
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
