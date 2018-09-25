package com.topwords

import org.scalatest._

class WordCloudTest extends WordSpec {

  val q = new WordQueue
  val c = new WordCloud(2, 2, 2, 0)

  "The size of a WordQueue" when {
    "given a new wordQueue" should {
      "be size 0" in {
        val result: Int = q.getQueueSize
        assert(result == 0)
      }
    }
    "given a new word of size < minlength" should {
      "still be size 0" in {
        q.add("a", c)
        val result: Int = q.getQueueSize
        assert(result == 0)
      }
    }
    "given a new word of size >= minlength" should {
      "be one greater than the previous if the queue is not full" in {
        q.add("aa", c)
        val result: Int = q.getQueueSize
        assert(result == 1)
      }
      "be equal to the size prior to if the queue is full" in {
        val resultBefore: Int = q.getQueueSize
        q.add("bb", c)
        c.removeOrDecrement(q.remove)
        val resultAfter: Int = q.getQueueSize
        assert(resultBefore == resultAfter)
      }
    }

    "A word is removed" should {
      "be one less than the previous" in {
        val resultBefore: Int = q.getQueueSize
        q.remove
        val resultAfter: Int = q.getQueueSize
        assert(resultBefore == (resultAfter + 1))
      }
    }

  }

  "A word cloud" when {
    "A new word is added" should {
      "not have the word already in the cloud" in {
        assert(!c.cloud.keySet.contains("cc"))
        c.insertOrIncrement("cc")
        assert(c.cloud.keySet.contains("cc"))
      }
      "have value 1 when the word is added" in {
        assert(c.cloud.exists(_ == ("cc", 1)))
      }
    }
    "An existing word is added" should {
      "have a value 1 greater than before" in {
        assert(c.cloud.exists(_ == ("cc", 1)))
        c.insertOrIncrement("cc")
        assert(c.cloud.exists(_ == ("cc", 2)))
      }
    }

    "A word is removed" should {
      "have a value 1 less than prior if the word had more than one entry" in {
        assert(c.cloud.exists(_ == ("cc", 2)))
        c.removeOrDecrement("cc")
        assert(c.cloud.exists(_ == ("cc", 1)))
      }
      "be removed if the value is 1" in {
        assert(c.cloud.exists(_ == ("cc", 1)))
        c.removeOrDecrement("cc")
        assert(!c.cloud.keySet.contains("cc"))
      }
    }
    "the cloud is printed" should {
      "print only the top howMany values" in {
        val t = new WordCloud(3, 2, 5, 0)
        val qt = new WordQueue
        val words: Array[String] = Array("aa", "bb", "cc", "aa", "bb")
        for (word <- words) {
          qt.add(word.toUpperCase(), t)
        }
        assert(t.getOutputString.equals("AA: 2 BB: 2 CC: 1 "))
        qt.add("dd".toUpperCase, t)
        t.removeOrDecrement(qt.remove)
        assert(t.getOutputString.equals("BB: 2 DD: 1 AA: 1 "))
      }
    }
  }

}
