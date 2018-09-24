object topwords {
  def main(args: Array[String]) {
    val param = Array(10, 6, 1000, 0)
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
    val outputDelay = param(3)

    val cloud = new WordCloud(howMany, minLength, lastNWords, outputDelay)
    cloud.handleInput
  }
}
