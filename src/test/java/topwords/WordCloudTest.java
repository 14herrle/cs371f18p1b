package topwords;

import org.junit.Test;

import static org.junit.Assert.*;


public class WordCloudTest {

    private WordCloud testcloud;
    private WordQueue testqueue;
    private String[] testInput = {"aaa", "aa", "aaa", "bb", "bbb", "bb", "ccc", "cc"};

    public void tearDown() throws Exception {
        testcloud = null;
        testqueue = null;
    }
    @Test
    public void testStandardInput() {
        testcloud = new WordCloud(3, 6);
        testqueue = new WordQueue();
        for (String s: testInput) {
            testqueue.addWord(s);
            testcloud.insertOrIncrement(s);
            if (testqueue.getQueueSize() == 6) {
                testcloud.removeOrDecrement(testqueue.removeWord());
            }
        }
        assertEquals("bb: 2 cc: 1 ccc: 1 ", testcloud.getOutputString() );
    }
}
