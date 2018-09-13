package topwords;

import java.util.LinkedList;
import java.util.Queue;

public class WordQueue {
    Queue<String> wordQueue;
    public WordQueue() {
        wordQueue = new LinkedList<>();
    }

    public void addWord(String w) { wordQueue.add(w);}

    public String removeWord() {
        return wordQueue.remove();
    }

    public int getQueueSize() { return wordQueue.size();}
}
