package pl.comp.datalog.dto;


import pl.comp.datalog.model.Rule;

/**
 * Created by Damian Ratajczak
 */
public class RuleDTO {

    private String id;
    private String value;

    public RuleDTO(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public RuleDTO() {
    }

    public RuleDTO(Rule rule) {
        this.id = rule.getId();
        this.value = rule.getValue();
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
        return value;
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
     * @param value New value of value.
     */
    public void setValue(String value) {
        value = value.trim();
        if(!value.endsWith(".")) value += ".";
        this.value = value;
    }
}
