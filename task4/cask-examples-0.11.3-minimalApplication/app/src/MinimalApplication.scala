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

  initialize()
