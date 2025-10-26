package app
object MinimalApplication extends cask.MainRoutes:

  def checkIsSorted(values: Seq[Int], order: String): Boolean = {
    var isSorted = true

    if (order == "ascending") {
      for (i <- 0 to values.length - 2) {
        if (values(i) > values(i + 1)) {
          isSorted = false
        }
      }
    } else if (order == "descending") {
      for (i <- 0 to values.length - 2) {
        if (values(i) < values(i + 1)) {
          isSorted = false
        }
    }
    }
    isSorted
  }

  @cask.postJson("/is-sorted")
  def isSorted(values: Seq[Int], order: String) = {
    var isSorted = true
    if (values.length > 1) {
      isSorted = checkIsSorted(values, order)
    }
    ujson.Obj("isSorted" -> isSorted)
  }  

  initialize()
