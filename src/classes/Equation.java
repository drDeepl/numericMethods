package classes;

public class Equation {

  private double d0;
  private double d1;
  private double d2;
  private double d3;

  public Equation() {}

  public Equation(double d3, double d2, double d1, double d0) {
    this.d0 = d0;
    this.d1 = d1;
    this.d2 = d2;
    this.d3 = d3;
  }

  public double apply(double x) {
    double result = d3 * Math.pow(x, 3) + d2 * Math.pow(x, 2) + d1 * x + d0;
    
    return result;
  }

  public double secondDerivative(double x, double h) {
    double f1 = apply(x + h);
    double f2 = apply(x);
    double f3 = apply(x - h);
    return (f1 - 2 * f2 + f3) / (h * h);
  }

  public double derivEquation(double x) {
    return d3 * 3 * Math.pow(x, 2) + d2 * 2 * x + d1;
  }

  public String printEquation() {
    double[] coeffs = { d3, d2, d1, d0 };
    String equation = "";
    for (int i = 0; i < coeffs.length; i++) {
      double value = coeffs[i];
      if (value > 0) {
        if (i != 0) {
          equation += " + ";
        }
      }
      equation += value;
      int degree = coeffs.length - 1 - i;
      if (degree > 0) {
        equation += "x";
        if (degree > 1) {
          equation += "^" + degree;
        }
      }
    }
    return equation;
  }
}
