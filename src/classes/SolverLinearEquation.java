package classes;

public class SolverLinearEquation {

  private Equation equation;
  private double eps;
  private int current_iters;
  private int maxIterations = 1000;

  public SolverLinearEquation(Equation equation, double eps) {
    this.equation = equation;
    this.eps = eps;
  }

  private void clearCurrentIters() {
    current_iters = 0;
  }

  public int getIters() {
    return current_iters;
  }

  public double findRootByHalfDevision(double a, double b) {
    clearCurrentIters();
    if (equation.apply(a) * equation.apply(b) >= 0) {
      throw new IllegalArgumentException("Theorem violation");
    }

    double c;
    for (int i = 0; i < maxIterations; i++) {
      c = (a + b) / 2; // middle point
      double fa = equation.apply(a); // function value at 'a'
      double fb = equation.apply(b); // function value at 'b'
      double fc = equation.apply(c); // function value at 'c'
      if (Math.abs(fc) < eps) { // tolerance check
        return c;
      }
      if (fa * fc < 0) { // root is in the left half-interval
        b = c;
      } else if (fb * fc < 0) { // root is in the right half-interval
        a = c;
      } else { // no root in the given interval
        return Double.NaN;
      }
    }
    return Double.NaN;
  }

  public double findRootByChord(double a, double b) {
    clearCurrentIters();
    double a_equation = equation.apply(a);
    double b_equation = equation.apply(b);
    if (Math.signum(a_equation * b_equation) > 0) {
      throw new IllegalArgumentException("Theorem violation");
    }
    double c_equation;
    double c1 = a;
    double c2;
    while (true) {
      a_equation = equation.apply(a);
      b_equation = equation.apply(b);
      c2 = a - (a_equation * (a - b)) / (a_equation - b_equation);
      c_equation = equation.apply(c2);
      if (Math.abs(c2 - c1) < eps) {
        // System.out.println("Root: " + c2 + "\nSteps: " + steps);
        return c2;
      }
      if (Math.signum(a_equation * b_equation) < 0) {
        b = c2;
      } else {
        a = c2;
      }
      c1 = c2;
      current_iters++;
    }
  }

  public double solveByTangentMethod(double x0, int iterations) {
    double x = x0;
    for (int i = 0; i < iterations; i++) {
      double fx = equation.apply(x);
      double fDeriv = equation.derivEquation(x);
      x = x - fx / fDeriv;
    }
    return x;
  }
}
