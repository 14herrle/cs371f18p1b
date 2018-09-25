import java.nio.charset.UnmappableCharacterException

import scala.collection.mutable.Map

class WordCloud(val howMany: Int, val minLength: Int, val lastNWords: Int, val outputDelay: Int) {

  var queue = new WordQueue
  var cloud: Map[String, Int] = Map.empty[String, Int]

  def handleInput = { //TODO: Make this testable
    import sun.misc.Signal
    Signal.handle(new Signal("INT"), _ => scala.sys.exit())
    try {
      val lines = scala.io.Source.stdin.getLines
      val words = lines.flatMap(_.split("(?U)[^\\p{Alpha}0-9']+"))
      var count = 0
      for (word <- words) {
        if (word.length >= minLength) {
          queue.add(word.toUpperCase())
          insertOrIncrement(word.toUpperCase())
          if (queue.getQueueSize == lastNWords) {
            if (count == outputDelay || !lines.hasNext) {
              println(getOutputString)
              count = 0
            } else {
              count += 1
            }
            removeOrDecrement(queue.remove)
          }
        }
      }
    } catch {
      case _: UnmappableCharacterException =>
    }
  }

  def insertOrIncrement(word: String): Unit = {
    val count = cloud.getOrElse(word, 0)
    cloud.update(word, (count + 1))
  }

  def removeOrDecrement(word: String) = {
    val count = cloud.getOrElse(word, 0)
    if (count == 1)
      cloud -= (word)
    else {
      cloud.update(word, (count - 1))
    }
  }
  def dumBubbleSort(a: Array[(String, Int)]): Array[(String, Int)] = {
    for (i <- 0 until a.length) {
      for (j <- 0 until a.length - 1) {
        if ((a(j)_2) < (a(j + 1)_2)) {
          val temp = a(j)
          a(j) = a(j + 1)
          a(j + 1) = temp
        }
      }
    }
    a
  }

  def getOutputString: String = {
    val sb = StringBuilder.newBuilder
    val t1 = cloud.toArray
    dumBubbleSort(t1)
    for (i <- 0 until t1.length) {
      if (i < howMany) {
        val s1 = t1(i) _1
        val s2 = t1(i) _2;
        sb.append(s1 + ": " + s2 + ": ")
      }
    }
    sb.toString()
  }
}