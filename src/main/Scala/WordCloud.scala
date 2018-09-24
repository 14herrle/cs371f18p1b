import java.nio.charset.UnmappableCharacterException

class WordCloud(val howMany: Int, val minLength: Int, val lastNWords: Int, val outputDelay: Int) {
  var queue = new WordQueue

  def handleInput = { //TODO: Make this testable
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
    //TODO: Implement thisjj
  }

  def removeOrDecrement(word: String) = {
    //TODO: Implement this
  }

  def getOutputString: String = {
    "Test successful. Queue size: " + queue.getQueueSize
  }

}