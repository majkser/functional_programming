package app
object MinimalApplication extends cask.MainRoutes:

  override def host = "0.0.0.0"
  override def port = 8080

  @cask.postJson("/tail")
  def tail(values: Seq[Int]) = {
    val list = LinkedList(values: _*)

    val res = list match {
      case Nil => Nil
      case Node(_, tail) => tail
    }

    def toSeq[A](list: LinkedList[A]): Seq[A] = list match {
      case Nil => Seq()
      case Node(head, tail) => head +: toSeq(tail)
    }
    
    ujson.Obj("tail" -> toSeq(res))
  }

  initialize()
