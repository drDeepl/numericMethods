import classes.Equation;
import classes.SolverLinearEquation;

public class Main {

  public static void main(String[] args) throws Exception {
    double x0 = -100;
    double h = 0.1;
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
      //   System.out.println(
      //     "CurrentX: " + valueOfCurrentX + "\n" + "NextX value: " + valueOfNextX
      //   );

      if (valueOfCurrentX * valueOfNextX < 0) {
        foundRoots++;
        System.out.println(
          "Interval: [" + currentX + "," + nextX + "]\nHaveSolver\n"
        );
        double xSolver = solver.findRootByHalfDevision(currentX, nextX);
        System.out.println("x = " + xSolver + "\n");
      }
      if (foundRoots == 3) {
        break;
      }
    }
    // int findRoots = 0;

    // while(findRoots != 3) {
    //     try{
    //     double root = solver.findRootByChord(a, b);
    //     System.out.println("Root" + findRoots+ " = " + root + "\nIters: " + solver.getIters());
    //     a -= h;
    //     b += h;
    //     findRoots++;
    // }
    //     catch (IllegalArgumentException e){
    //         b+=h;
    //     }

    // }
  }
}
