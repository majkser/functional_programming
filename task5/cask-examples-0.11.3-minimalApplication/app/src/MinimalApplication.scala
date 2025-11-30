package app
object MinimalApplication extends cask.MainRoutes:

  override def host = "0.0.0.0"
  override def port = 8080

  @cask.postJson("/count-nums")
  def countNums(nums: Seq[Int]) = {
    val map = nums.groupBy(identity).map((i, j) => (i, j.size))

    ujson.Obj(
      "count" -> map.map((i, j) => (i.toString, j)).toSeq
    )
  }

  initialize()
