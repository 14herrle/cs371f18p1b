package topwords;



import java.util.Arrays;
import java.util.Scanner;

/**
 * @author aherrle
 *
 */
public class topwords {

    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        int howmany, minlength, lastnwords;
        int[] param = new int[3];
        int i = 0;

        while (i < args.length && i < 3) {
            try {
                param[i] = Integer.parseInt(args[i]);

            } catch (NumberFormatException ex) {
                if (i < 3)
                    param[2] = 1000; //default lastnwords value
                if (i < 2)
                    param[1] = 6; //default minlength value
                if (i < 1)
                    param[0] = 10; //default  howmany value
                i = 3;
            }
            i++;
        }

        howmany = param[0];
        minlength = param[1];
        lastnwords = param[2];


        WordCloud c = new WordCloud(howmany, minlength, lastnwords);
        WordQueue q = new WordQueue();
        Scanner inputStream = new Scanner(System.in);
        try {
            while (inputStream.hasNext()) {
                String s = inputStream.next("\\p{L}+");
                if (s.length() >= minlength) {
                    q.addWord(s.toUpperCase());
                    c.insertOrIncrement(s.toUpperCase());
                    if (q.getQueueSize() == lastnwords) {
                        c.removeOrDecrement(q.removeWord());
                        System.out.println(c.getOutputString());
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}