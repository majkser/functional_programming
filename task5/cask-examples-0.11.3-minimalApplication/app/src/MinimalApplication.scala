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

  @cask.postJson("/cube-nums")
  def cubeNums(nums: Seq[Int]) = {
    val cubed = nums.map(i => Math.pow(i, 3).toInt)
    val map = cubed.groupBy(identity).map((i, j) => (i, j.size))
    
    ujson.Obj(
      "cubes" -> map.map((i, j) => (i.toString, j)).toSeq
    )
  }

  initialize()
