package azure;

import java.util.ArrayList;

public class Logic {

    private ArrayList<ConditionalAccessPolicy> conditionalAccessPolicyArrayList;

    public Logic() {
        conditionalAccessPolicyArrayList = new ArrayList<ConditionalAccessPolicy>();
    }

    public void addConditionalAccessPolicy(ConditionalAccessPolicy conditionalAccessPolicy) {
        conditionalAccessPolicyArrayList.add(conditionalAccessPolicy);
    }

    public ArrayList<ConditionalAccessPolicy> getConditionalAccessPolicyArrayList() {
        return conditionalAccessPolicyArrayList;
    }

}
