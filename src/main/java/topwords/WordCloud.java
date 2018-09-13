package topwords;



import java.util.HashMap;




public class WordCloud {
    private int howmany, minlength, lastnwords;

    HashMap<String, Integer> cloud;

    public WordCloud (int howmany, int minlength, int lastnwords) {
        this.howmany = howmany;
        this.minlength = minlength;
        this.lastnwords = lastnwords;

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

    }

    public String getOutputString() {

        return "";
    }
}
