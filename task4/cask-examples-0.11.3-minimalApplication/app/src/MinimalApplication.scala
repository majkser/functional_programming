package app
object MinimalApplication extends cask.MainRoutes:

  override def host = "0.0.0.0"
  override def port = 8080

  @cask.postJson("/append")
  def append(list: Option[Seq[Int]] = None, value: Option[Int] = None, index: Option[Int] = None) = {
    val baseList = list.getOrElse(Seq.empty[Int])
    val valueToInsert = value.getOrElse(0)
    val indexValue = index.getOrElse(baseList.length)
    
    val (front, back) = baseList.splitAt(indexValue)
    front ++ Seq(valueToInsert) ++ back

    ujson.Obj(
      "result" -> (front ++ Seq(valueToInsert) ++ back)
    )
  }

  @cask.postJson("/variance")
  def variance(list: Option[Seq[Int]] = None) = {
    
    def calculateVariance(baseList: Seq[Int]) = {
      val n = baseList.length
      val avg = baseList.sum.toDouble / n
      var variance = 0.0

      for (num <- baseList) {
        variance += Math.pow(num - avg, 2)
      }
      variance /= n

      ujson.Obj(
        "result" -> variance
      )
    }
    
    val baseList = list.getOrElse(Seq.empty[Int])
    if (baseList.isEmpty) {
      ujson.Obj(
        "result" -> "Can't calc variance from empty list ;("
      )
    } else {
      calculateVariance(baseList)
    }
  }

  initialize()
