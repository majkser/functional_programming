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

  def sumLists(list1: Seq[Int], list2: Seq[Int]): Seq[Int] = {
    val length = math.min(list1.length, list2.length)
    var result = Seq[Int]()
    for (i <- 0 until length) {
      result :+= (list1(i) + list2(i))
    }
    result
  }

  @cask.postJson("/sum-three-lists")
  def sumThreeLists(list1: Seq[Int], list2: Seq[Int], list3: Seq[Int]) = {
    ujson.Obj("summedList" -> sumLists(sumLists(list1, list2), list3))
  }

  initialize()
