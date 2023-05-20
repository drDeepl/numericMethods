package classes;

public class SolverLinearEquation {
    private Equation equation;
    private double eps;
    private int current_iters;
    private double current_x;
    private double h;

  
    public SolverLinearEquation(Equation equation, double eps, double h) {
      this.equation = equation;
      this.eps = eps;
      this.h = h;
    }
  
    public int getIters() {
      return current_iters;
    }

    public String printResult(){
      String result =  "x = " + current_x + "\n\t\t\tCount iters: " + current_iters + "\n";
      System.out.println(result);
      return result;
    }
  
    public double findRootByHalfDevision(double a, double b) {
      System.out.println("INFO: Finding root by half division");
      int iters = 0;
      if (equation.apply(a) * equation.apply(b) >= 0) {
        System.out.println("Theorem violation");
        return Double.NaN;
      }
      double c;
      while (true) {
        c = (a + b) / 2; // middle point
        double fa = equation.apply(a);
        double fb = equation.apply(b);
        double fc = equation.apply(c);
        if (Math.abs(fc) < eps) {
          current_iters = iters;
          current_x = c;
          return c;
        }
        if (fa * fc < 0) {
          b = c;
        } else if (fb * fc < 0) {
          a = c;
        } else {
          // No root in interval
          return Double.NaN;
        }
        iters++;
      }
    }
  
    public double findRootByChord(double a, double b) {
      System.out.println("INFO: Finding root by Chord");
      int iters = 0;
      double a_equation = equation.apply(a);
      double b_equation = equation.apply(b);
      if (Math.signum(a_equation * b_equation) > 0) {
        // throw new IllegalArgumentException("Theorem violation");
        return Double.NaN;
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
          current_iters = iters;
          current_x = c2;
          return c2;
        }
        if (Math.signum(a_equation * b_equation) < 0) {
          b = c2;
        } else {
          a = c2;
        }
        c1 = c2;
        iters++;
      }
    }
  
    public double findRootByNewton(double a, double b) {
      System.out.println("INFO: Finding root by Newton");
      double x = Double.NaN;
      if(equation.apply(a) * equation.secondDerivative(a,h) > 0){
        System.out.println("The condition fullfilled for a");
        x = a;
      }
      else if(equation.apply(b) * equation.secondDerivative(b,h) > 0){
        System.out.println("The condition fullfilled for b");
        x = b;
      }
      else{
        System.out.println("The condition don't fullfilled");
      }
      if(x != Double.NaN){
        int k = 0;
        while(true){
          x = x - (equation.apply(x)/equation.firstDerivative(x, h));
          k++;
          if(Math.abs(equation.apply(x)) < eps){
            current_iters = k;
            current_x = x;
            return x;
          }
        }
      }
      return x;
  }

  public double findRootByChordNewton(double a, double b) {
    System.out.println("INFO: Finding root by Chord-Newton");
    double a0 = a;
    double b0 = b;
    double fa0 = equation.apply(a0);
    double fb0 = equation.apply(b0);


    int iters = 0;   
    while(Math.abs(a0-b0) > eps){
      a0 = a0 -(fa0/ equation.firstDerivative(a0, h));
      b0 = b0 -(((a0-b0)/(fa0-fb0)) * fb0);
      fa0 = equation.apply(a0);
      fb0 = equation.apply(b0);      
      iters++;
    }    
    double x = (a0+b0)/2;
    current_iters = iters;
    current_x = x;
    return x;
    
  }
    
}
