package com.topwords

import org.scalatest._

class WordCloudTest extends WordSpec {

  /*test("A new word cloud should be empty") {
    assert((new com.topwords.WordCloud).getSize() == 0)
  }

  test("A new word queue should be empty") {
    assert((new com.topwords.WordQueue).getQueueSize() == 0)
  }


  test("the addWord method of com.topwords.WordQueue adds a new word to the queue") {
    val tQ = new com.topwords.WordQueue
    tQ.add("word")
    assert(tQ.getQueue.equals(new Queue.empty[String] += "word"))
  }
  */

  val q = new WordQueue
  val c = new WordCloud

  "The the function getQueueSize" when {
    "given a new wordQueue" should {
      "be size 0" in {
        val result: Int = q.getQueueSize
        assert(result == 0)
      }
    }
  }

}
