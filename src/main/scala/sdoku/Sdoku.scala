package sdoku
import java.util.concurrent.TimeUnit.NANOSECONDS

object Main extends App:
  //val sdoku = Solver.MakeSdoku
  val sdoku = Solver.TestSdoku
  Solver.PrintSdoku(sdoku)

  //val sdokuTemp : SdokuCell = Array.ofDim[Int](9, 9)
  //sdoku.copyToArray(sdokuTemp)
  /*val sdokuTemp = sdoku.map(_.clone)
  sdoku(0)(0) = 1
  sdoku(0)(2) = 8
  sdoku(0)(3) = 3

  Solver.PrintSdoku(sdoku)
  Solver.PrintSdoku(sdokuTemp)*/
  val tik0 = System.nanoTime()
  val solved = NaiveSolver.SolveSdoku(sdoku)
  val tik1 = System.nanoTime()
  println("NaiveSolver: " + NANOSECONDS.toMillis(tik1 - tik0) + " ms")
  Solver.PrintSdoku(solved)
  
  val tik2 = System.nanoTime()
  val solved1 = FastSolver.SolveSdoku(sdoku)
  val tik3 = System.nanoTime()
  println("FastSolver: " + NANOSECONDS.toMillis(tik3 - tik2) + " ms")
  Solver.PrintSdoku(solved1)

  val tik4 = System.nanoTime()
  val solved2 = FastSolver1.SolveSdoku(sdoku)
  val tik5 = System.nanoTime()
  println("FastSolver1: " + NANOSECONDS.toMillis(tik5 - tik4) + " ms")
  Solver.PrintSdoku(solved2)
  /*sdoku(0)(0) = 1
  sdoku(0)(2) = 8
  sdoku(0)(3) = 3
  println(Solver.GetAvailableNumber(sdoku, 0, 4))*/

end Main