package pl.comp.datalog.model;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.comp.datalog.dto.RuleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian Ratajczak
 */
@Document(collection = "rule")
public class Rule {

    @Id
    private String id;
    private String head;
    private List<String> body;
    //private String value;

    private String[] valueParser(String value)
    {
        String[] parts = value.split(":-");
        String head = parts[0];
        String body = parts[1];
        String[] subgoals = body.split(",");
        return (String[]) ArrayUtils.addAll(new String[] {head}, subgoals);
    }
    public Rule(String id, String value) {
        this.id = id;
        this.body = new ArrayList<String>();
        //this.value = value;
        String[] a = valueParser(value);
        boolean h = true;
        for (String i : a)
        {
            if(h) { h = false; this.head = i.trim();}
            else this.body.add(i.replace('.', ' ').trim());
        }
    }

    public Rule() {
    }

    public Rule(RuleDTO ruleDTO) {
        this.id = ruleDTO.getId();
        this.body = new ArrayList<>();

        String value = ruleDTO.getValue();
        String[] a = valueParser(value);
        boolean h = true;
        for (String i : a)
        {
            if(h) { h = false; this.head = i.trim();}
            else this.body.add(i.replace('.', ' ').trim());
        }
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets value.
     *
     * @return Value of value.
     */
    public String getValue() {
        StringBuilder s = new StringBuilder(this.head + " :- ");
        for (String i:this.body) {
            s.append(i).append(", ");
        }
        return s.toString().replaceAll(", $", ".");
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets new value.
     *
     * @param value New value of our rule.
     */
    public void setValue(String value) {
        String[] a = valueParser(value);
        this.body = new ArrayList<>();
        boolean h = true;
        for (String i : a)
        {
            if(h) { h = false; this.head = i.trim();}
            else this.body.add(i.replace('.', ' ').trim());
        }
    }
}
