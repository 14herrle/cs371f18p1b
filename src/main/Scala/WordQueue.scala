class WordQueue {
  import scala.collection.mutable.Queue
  var q: Queue[String] = Queue.empty[String]

  def add(word: String) = {
    q.enqueue(word)
  }

  def remove = {
    q.dequeue
  }

  def getQueueSize: Int = {
    q.length
  }
}