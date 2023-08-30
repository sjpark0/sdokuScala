package sdoku

object FastSolver extends Solver{
  def SolveSdoku(sdoku : SdokuCell) : SdokuCell = {
    val emptyList = Solver.MakeEmptyList(sdoku)
    val assignList = Nil

    val result = SolveSdoku1(assignList, emptyList)
    Assign(sdoku, result._2)
  }
  
  def Assign(sdoku : SdokuCell, assignList : List[Coord]) : SdokuCell = {
    val sdokuTemp = sdoku.map(_.clone)
    assignList.foreach(coord => sdokuTemp(coord._1)(coord._2) = coord._5.head)
    sdokuTemp
  }
  
  def UpdateAvailList(assignElem : Coord, emptyList : List[Coord]) : List[Coord] = {
    emptyList.map(elem => {
      if elem._1 == assignElem._1 || elem._2 == assignElem._2 || (elem._3 == assignElem._3 && elem._4 == assignElem._4) then
        (elem._1, elem._2, elem._3, elem._4, elem._5.filter(_ != assignElem._5.head))
      else elem
    })
  }
  
  def SolveSdoku1(assignList : List[Coord], emptyList : List[Coord]) : (Int, List[Coord]) = {
    if emptyList.isEmpty then (1, assignList) else{
      val availList = emptyList.head._5
      if availList.isEmpty then (0, List[Coord]()) else{
        availList.foldLeft(0, List[Coord]()) ((b, a) => {
          val assignElem : Coord = (emptyList.head._1, emptyList.head._2, emptyList.head._3, emptyList.head._4, List(a))
          val tmp = SolveSdoku1(assignElem :: assignList, UpdateAvailList(assignElem, emptyList.tail))
          if tmp._1 == 1 then tmp else b
        })
      }
    }
  }    
}