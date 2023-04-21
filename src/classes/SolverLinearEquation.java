package classes;

public class SolverLinearEquation {
    private Equation equation;
    private double eps;
    private int current_iters;

    public SolverLinearEquation(Equation equation, double eps){
        this.equation = equation;
        this.eps = eps;
    }



    public int getIters(){
        return current_iters;
    }

    public double findRootByHalfDevision(double a, double b){
        if(equation.apply(a)*equation.apply(b) >=0){
            throw new IllegalArgumentException("Theorem violation");

        }
        while(true){
            double c = (a+b)/2;
            double f_a = equation.apply(a);
            double f_b = equation.apply(b);
            double f_c = equation.apply(c);
            if(Math.abs(f_c< eps)){
                return c;
            }
            if(f_a * f_b < 0){
                b = c;
            }
            else{
                a = c;
            }
        }
    }

    public double findRootByChord(double a, double b){
        int iters = 0;
        double a_equation = equation.apply(a);
        double b_equation = equation.apply(b);
        if(Math.signum(a_equation * b_equation) > 0){
            throw new IllegalArgumentException("Theorem violation");
        }
        double c_equation;
        double c1 = a;
        double c2;
        while(true){
            a_equation = equation.apply(a);
            b_equation = equation.apply(b);
            c2 = a - (a_equation * (a-b))/(a_equation - b_equation);
            c_equation = equation.apply(c2);
            if(Math.abs(c2 - c1)< eps){
                // System.out.println("Root: " + c2 + "\nSteps: " + steps);
                current_iters = iters;
                return c2;
                                
            }
            if(Math.signum(a_equation * b_equation)< 0){
                b = c2;
            }
            else{
                a=c2;
            }
            c1=c2;
            iters++;



        }
    }
}
