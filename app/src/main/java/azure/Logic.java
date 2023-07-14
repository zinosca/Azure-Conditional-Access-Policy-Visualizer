package azure;


import azure.conditional.assignments.Assignments;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> parseJsonToMap(String jsonStr) {
        Map<String, String> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(jsonStr);

            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                result.put(field.getKey(), field.getValue().toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }




    public void inputProperty(String jsonStr) {
        // Ersetzen der öffnenden Klammern, um eindeutige Trennzeichen zu setzen
        String replacedStr = jsonStr.replace("{", "#@{");

        // Aufteilen des Strings anhand des Trennzeichens
        String[] splitStr = replacedStr.split("#@");

        // Konvertieren des Arrays in eine ArrayList und Entfernen des ersten (leeren) Elements
        ArrayList<String> capList = new ArrayList<>(Arrays.asList(splitStr));
        capList.remove(0);

        for(String capText : capList ){
            parseJsonToMap(capText);
            conditionalAccessPolicyArrayList.add(new ConditionalAccessPolicy(parseJsonToMap(capText).get("displayName"),parseJsonToMap(capText).get("id"),parseJsonToMap(capText).get("state"), new Assignments());
        }
    }










}
