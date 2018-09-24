package src

import org.scalatest.funsuite



class WordCloudTest extends FunSuite {

  test("A new word cloud should be empty") {
    assert((new WordCloud).getSize() == 0)
  }

  test("A new word queue should be empty") {
    assert((new WordQueue).getQueueSize() == 0)
  }

  test("the addWord method of WordQueue adds a new word to the queue") {

  }

}
