import classes.Equation;
import classes.SolverLinearEquation;

public class App {
    public static void main(String[] args) throws Exception {
    double x0 = -700;
    double h = 0.001;
    double eps = 0.0001;
    int foundRoots = 0;
    String result = "";

    Equation F = new Equation(1, -2.5, -1.2, 3);

    String equation = F.printEquation();

    SolverLinearEquation solver = new SolverLinearEquation(F, eps, h);
    int maxIters = 10000000;
    int iters = 0;
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
      if (foundRoots == 3 || iters == maxIters) {
        break;
      }
      if (valueOfCurrentX * valueOfNextX < 0) {
        foundRoots++;
        result += "Root" + foundRoots + ":\n";
        System.out.println(
            "Interval: [" + currentX + "," + nextX + "]\nHave solve\n");
        // double xSolveHalfDevision = 
        result += "\tMethods:\n";
        result += "\t\tHalf Devision:\n";
        double xHalfDevision = solver.findRootByHalfDevision(currentX, nextX);
        // System.out.println("x = " + xSolveHalfDevision + "\nCount iters: " + solver.getIters() + "\n");
        result += "\t\t\t" + solver.printResult() + "\n";
        // double xSolveChord = 
        result += "\t\t\tChord:\n";
        double xChord = solver.findRootByChord(currentX, nextX);
        result += "\t\t\t" + solver.printResult() + "\n";
        // System.out.println("x = " + xSolveChord + "\nCount iters: " + solver.getIters() + "\n");
        // solver.printResult();
        // double xSolveByNewton = 
        result += "\t\tNewton:\n";
        double xNewton = solver.findRootByNewton(currentX, nextX);
        result += "\t\t\t" + solver.printResult() + "\n";
        // System.out.println("x = " + xSolveByNewton + "\nCount iters: " + solver.getIters() + "\n");
        // solver.printResult();
        result += "\t\tChordNewton:\n";
      double xChordNewton = solver.findRootByChordNewton(currentX, nextX);
      result += "\t\t\t" + solver.printResult() + "\n";  
      // solver.printResult();
      }
      iters++;
    }
    System.out.println("Common count of iterations: " + iters);
    System.out.println(result);
  }
}

