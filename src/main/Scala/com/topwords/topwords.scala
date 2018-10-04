package com.topwords

object topwords {
  def main(args: Array[String]) {
    val param = Array(10, 6, 1000, 1)
    for (i <- 0 until args.length) {
      try
        param(i) = args(i).toInt
      catch {
        case _: NumberFormatException =>
          println("ERROR: invalid argument supplied")
          return
        case _: ArrayIndexOutOfBoundsException =>
          println("ERROR: too many arguments supplied")
          return
      }
    }
    val howMany = param(0)
    val minLength = param(1)
    val lastNWords = param(2)
    val outputDelay = if (param(3) > 0) param(3) else 1

    val cloud = new WordCloud(howMany, minLength, lastNWords, outputDelay)
    cloud.handleInput
  }
}
