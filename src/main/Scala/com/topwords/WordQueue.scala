package com.topwords

class WordQueue {
  import scala.collection.mutable.Queue
  var q: Queue[String] = Queue.empty[String]

  def add(word: String, cloud: WordCloud): Unit = {
    if (word.length >= cloud.minLength) {
      q.enqueue(word)
      cloud.insertOrIncrement(word)
    }
  }

  def remove = {
    q.dequeue
  }

  def getQueueSize: Int = {
    q.length
  }
}