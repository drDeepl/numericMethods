package classes;

public class Equation {
    private double d0;
    private double d1;
    private double d2;
    private double d3;

    public Equation(){}

    public Equation(double d0, double d1, double d2, double d3){
        this.d0=d0;
        this.d1=d1;
        this.d2=d2;
        this.d3=d3;
    }

 
    // public double apply(double x){
    //     return d3 * Math.pow(x,3) + d2 * Math.pow(x,2) + d1 * x + d0;
    // }

    public double apply(double x){
        return x * x - Math.exp(x);
    }
}
