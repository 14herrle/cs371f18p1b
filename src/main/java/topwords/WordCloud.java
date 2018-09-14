package topwords;

import java.util.HashMap;


public class WordCloud {
    private int howmany;

    HashMap<String, Integer> cloud;

    public WordCloud (int howmany, int lastnwords) {
        this.howmany = howmany;

        cloud = new HashMap(lastnwords / 3);
    }

    public void insertOrIncrement (String w) {
        Integer count = cloud.get(w);
        if (count == null) {
            cloud.put(w, 1);
        } else {
            cloud.put(w, count + 1);
        }
    }

    public void removeOrDecrement (String w) {
        Integer count = cloud.get(w);
        if (count > 1)
            cloud.put(w, count - 1);
        else
            cloud.remove(w);

    }

    public String getOutputString() {
        String outputString = "";
        StringBuilder sb = new StringBuilder(outputString);
        cloud
                .entrySet()
                .stream()
                .sorted((x, y) ->
                        y.getValue().compareTo(x.getValue()))
                .limit(howmany)
                .forEach(x -> sb.append(x.getKey() + ": " + x.getValue() + " "));
        outputString = sb.toString(); //+ "   Words in Cloud: " + cloud.size();
        return outputString;
    }
}
