// 1 lab = 6.15 ball;

import classes.Equation;
import classes.SolverLinearEquation;

public class Main {

  public static void main(String[] args) throws Exception {
    double x0 = -500;
    double h = 0.001;
    double eps = 0.001;
    int foundRoots = 0;

    Equation F = new Equation(1, -2.5, -1.2, 3);

    String equation = F.printEquation();

    SolverLinearEquation solver = new SolverLinearEquation(F, eps);

    System.out.println("Current Equation: " + equation + "\n");
    while (x0 != x0 * -1) {
      double currentX = x0;
      double valueOfCurrentX = F.apply(currentX);
      x0 += h;
      double nextX = x0;
      double valueOfNextX = F.apply(nextX);
      // System.out.println(
      // "CurrentX: " + valueOfCurrentX + "\n" + "NextX value: " + valueOfNextX
      // );
      if (foundRoots == 3) {
        break;
      }
      if (valueOfCurrentX * valueOfNextX < 0) {
        foundRoots++;
        System.out.println(
            "Interval: [" + currentX + "," + nextX + "]\nHaveSolver\n");
        double xSolverHalfDevision = solver.findRootByHalfDevision(currentX, nextX);
        System.out.println("x by Half Devision = " + xSolverHalfDevision + "\n");

        double xSolverChord = solver.findRootByChord(currentX, nextX);
        System.out.println("x by Chord = " + xSolverChord + "\n");
        double y = F.apply(xSolverChord);
        System.out.println("y = " + y);
      }

    }
  }
}
