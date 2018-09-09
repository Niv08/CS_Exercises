import std.StdOut;
import polynomials.Polynomial;

public class Parabola extends Polynomial {

    //1 for smiling, -1 for sad.
    protected int sign;

    public Parabola(int a, int b, int c) {
        super(new int[] {c, b, a});
        if (a == 0) {
            throw new RuntimeException("Not a real parabola!");
        }
        this.sign = (a > 0 ? 1 : -1);
    }

    /**
     * returns the sign of the parabola
     * @return
     */
    public int sign() {
        return this.sign;
    }


    /**
     * calculates the discriminant of the parabola
     * @return
     */
    public double discriminant() {
        double a = this.getCoefficient(0);
        double b = this.getCoefficient(1);
        double c = this.getCoefficient(2);

        return b*b - 4 * a * c;
    }


    /**
     * finds a root using quadratic formula
     * @return double
     */
    public double root() {

        if (this.discriminant() < 0) {
            return Double.NaN;
        }

        double a = this.getCoefficient(2);
        double b = this.getCoefficient(1);

        return ((- b + Math.sqrt(this.discriminant()))/(2*a));
    }

    /**
     * finds the global minimum/maximum of the parabola
     * @return if a > 0 - returns global minimum
     *          if a < 0 - returns global maximum
     */
    public double extremum() {

        double a = this.getCoefficient(2);
        double b = this.getCoefficient(1);

        double xExtremePoint = - b / (2*a);
        return this.value(xExtremePoint);

    }

    public static void main(String[] args) {
//      for testing purposes

        Parabola p1 = new Parabola(1,0,0);
        StdOut.println(p1);
        StdOut.println("d(p1) = " + p1.discriminant());
        StdOut.println("root(p1) = " + p1.root());
        StdOut.println(p1.extremum());
        p1.plot(-5, 5, 100);

    }

}
