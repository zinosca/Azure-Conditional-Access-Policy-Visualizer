package azure.conditional.assignments.conditions;

public class FilteredDevicesRule{
    private String andOr;
    private String operator;
    private String value;

    public FilteredDevicesRule(String andOr, String operator, String value){
        this.andOr = andOr;
        this.operator = operator;
        this.value = value;
    }

    public String getAndOr(){
        return andOr;
    }

    public String getOperator(){
        return operator;
    }

    public String getValue(){
        return value;
    }

}
