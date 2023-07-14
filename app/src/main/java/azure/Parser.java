package azure;

import java.util.ArrayList;

public class Parser {

    ArrayList<Integer> indexNumberList = new ArrayList<>();

    public Parser(ArrayList<String> capElements) {
        indexNumberList.add(getFirstIndexOfStringContaining(capElements, "\"id\""));
    }



    public int getFirstIndexOfStringContaining(ArrayList<String> list, String element) {
        int result = -1;  // Rückgabe von -1, wenn kein String das gesuchte Element enthält

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str.contains(element)) { // Überprüfen, ob der String das gesuchte Element enthält
                result = i;  // Speichern des Indexes
                break;  // Schleife beenden
            }
        }

        return result;
    }


}
