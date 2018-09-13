package topwords;

import sun.misc.Signal;

import java.util.Scanner;

/**
 * @author aherrle
 *
 */
public class topwords {

    public static void main(String[] args) {
        Signal signal = new Signal("INT");
        Signal.handle(signal, signal1 -> {System.exit(0);});
        int howmany, minlength, lastnwords, outputDelay;
        int[] param = {10, 6, 1000, 0};
        int i = 0;

        while (i < args.length && i < 4) {
            try {
                param[i] = Integer.parseInt(args[i]);

            } catch (NumberFormatException ex) {
                i = 4;
            }
            i++;
        }


        howmany = param[0];
        minlength = param[1];
        lastnwords = param[2];
        outputDelay = param[3];


        WordCloud c = new WordCloud(howmany, lastnwords);
        WordQueue q = new WordQueue();
        Scanner inputStream = new Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+");
        try {
            int count = 0;
            while (inputStream.hasNext()) {
                String s = inputStream.next();
                //System.out.println(s);
                if (s.length() >= minlength) {
                    q.addWord(s.toUpperCase());
                    c.insertOrIncrement(s.toUpperCase());
                    if (q.getQueueSize() == lastnwords) {
                        c.removeOrDecrement(q.removeWord());
                        if(count == outputDelay || !inputStream.hasNext()) {
                            System.out.println(c.getOutputString());
                            count = 0;
                        }
                        else
                            count++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}