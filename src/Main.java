import classes.Equation;
import classes.SolverLinearEquation;

public class Main {
    public static void main(String[] args) throws Exception {
        double a = -1;
        double b = 1;
        double eps = 0.1;
        double h = 1;
        Equation F = new Equation();
        int findRoots = 0;
        SolverLinearEquation solver = new SolverLinearEquation(F, eps);
        while(findRoots != 3) {
            try{

            double root = solver.findRootByChord(a, b);
            System.out.println("Root" + findRoots+ " = " + root + "\nIters: " + solver.getIters());
            a -= h;
            b += h;
            findRoots++;
        }
            catch (IllegalArgumentException e){
                b+=h;
            }

        }
    }
}
