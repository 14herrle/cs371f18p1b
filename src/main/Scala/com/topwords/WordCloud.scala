package com.topwords

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
      processWords(words, false)
    } catch {
      case _: UnmappableCharacterException =>
    }
  }
  def processWords(words: Iterator[String], suppressOutput: Boolean): Int = {
    var count = outputDelay
    for (word <- words) {
      queue.add(word.toUpperCase, this)
      if (queue.getQueueSize == lastNWords) {
        if (count == outputDelay) {
          if (!suppressOutput) { println(getOutputString); }
          count = 0
        } else {
          count += 1
        }
        removeOrDecrement(queue.remove)
      }
    }
    if (count != 0 && !suppressOutput) {
      println(getOutputString)
    }
    count
  }

  def insertOrIncrement(word: String): Unit = {
    val count = cloud.getOrElse(word, 0)
    cloud.update(word, (count + 1))
  }

  def removeOrDecrement(word: String): Unit = {
    val count = cloud(word)
    if (count == 1)
      cloud -= word
    else {
      cloud.update(word, count - 1)
    }
  }

  def getOutputString: String = {
    val sb = StringBuilder.newBuilder
    //val t1 = cloud.toArray
    val t1 = cloud.toSeq.sortWith(_._2 > _._2)
    //actuallyWorkingQuickSort(t1)
    for (i <- 0 until t1.length) {
      if (i < howMany) {
        val s1 = t1(i) _1
        val s2 = t1(i) _2;
        sb.append(s1 + ": " + s2 + " ")
      }
    }
    sb.toString()
  }
}

/*

*/

/* Rest In Peace Hacked Together Bubble Sort. Your Contributions Will Not Be Forgotten
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
 */
/*
def actuallyWorkingQuickSort(xs: Array[(String, Int)]): Array[(String, Int)] = {
  def swap(i: Int, j: Int) = {
    val t = xs(i); xs(i) = xs(j); xs(j) = t;
  }
  def sort1(l: Int, r: Int): Any = {
    val pivot = (xs((l + r) / 2)_2);
    var i = l;
    var j = r;
    while (i <= j) {
      while ((xs(i)_2) > pivot) { i = i + 1 }
      while ((xs(j)_2) < pivot) { j = j - 1 }
      if (i <= j) {
        swap(i, j);
        i = i + 1;
        j = j - 1;
      }
    }
    if (l < j) sort1(l, j);
    if (j < r) sort1(i, r);

  }
  sort1(0, xs.length - 1);
  xs
}
*/ 