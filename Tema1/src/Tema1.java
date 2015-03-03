import java.util.Scanner;
/**
 * Created by Adelina on 22.02.2015.
 */
public class Tema1 {

    private static double f(double x){
        return ((1.0 / 2.0) + ((1.0 / 4.0) * x * x) - (x * Math.sin(x)) + ((1.0 / 2.0) * Math.cos(2.0 * x)));

    }

    private static double df(double x) {
        return 0.5 * x - Math.sin(x) - x * Math.cos(x) - 2 * Math.sin(x) * Math.cos(x);
    }

    public static void metodaBisectiei(double a0, double b0, double error){
        Double[] a = new Double[1000];
        Double[] b = new Double[1000];
        Double[] c = new Double[1000];

        a[0] = a0;
        b[0] = b0;
        c[0] = (a[0] + b[0]) / 2.0;

        int n = 1;
        while(true){
            if(f(c[n - 1]) == 0.0) break;
            else if(f(a[n - 1]) * f(c[n - 1]) < 0.0){
                a[n] = a[n - 1];
                b[n] = c[n - 1];
                c[n] = (a[n] + b[n]) / 2.0;
            }
            else {
                a[n] = c[n - 1];
                b[n] = b[n - 1];
                c[n] = (a[n] + b[n]) / 2.0;
            }

            if(Math.abs(c[n] - c[n - 1]) < error && Math.abs(f(c[n])) < error)
                break;
            n++;
        }

      System.out.println("Pasul " + n + ": " + c[n]);

    }

    public static void regulaFalsi(double a0, double b0, double error){
        Double[] a = new Double[1000];
        Double[] b = new Double[1000];
        Double[] c = new Double[1000];

        a[0] = a0;
        b[0] = b0;
        c[0] = (a[0] * f(b[0]) - b[0] * f(a[0])) / (f(b[0]) - f(a[0]));

        int n = 1;
        while(true){
            if(f(c[n - 1]) == 0.0) break;
            else if(f(a[n - 1]) * f(c[n - 1]) < 0.0){
                a[n] = a[n - 1];
                b[n] = c[n - 1];
                c[n] = (a[n] * f(b[n]) - b[n] * f(a[n])) / (f(b[n]) - f(a[n]));
            }
            else {
                a[n] = c[n - 1];
                b[n] = b[n - 1];
                c[n] = (a[n] * f(b[n]) - b[n] * f(a[n])) / (f(b[n]) - f(a[n]));
            }

            if(Math.abs(c[n] - c[n - 1]) < error && Math.abs(f(c[n])) < error)
                break;
            n++;
        }

        System.out.println("Pasul " + n + ": " + c[n]);
    }

    public static void metodaCoardei(double a0, double b0, double error){
        Double[] x = new Double[1000];

        x[0] = a0;
        x[1] = b0;

        int n = 1;
        while(true){
            x[n + 1] = (x[0] * f(x[n]) - x[n] * f(x[0])) / (f(x[n]) - f(x[0]));

            if(Math.abs(x[n] - x[n - 1]) < error && Math.abs(f(x[n])) < error)
                break;
            n++;
        }

        System.out.println("Pasul " + n + ": " + x[n]);
    }

    public static void metodaSecantei(double a0, double b0, double error){
        Double[] x = new Double[1000];

        x[0] = a0;
        x[1] = b0;

        int n = 1;
        do {
            x[n + 1] = (x[n - 1] * f(x[n]) - x[n] * f(x[n - 1])) / (f(x[n]) - f(x[n - 1]));
            n ++;
        } while(f(x[n]) >= error && Math.abs(x[n] - x[n - 1]) >= error);

        System.out.println("Pasul " + n + ": " + x[n]);
        System.out.println("Pasul " + n + ": " + -x[n]);
    }

    public static void metodaNewton(double a0, double error){
        Double[] x = new Double[1000];

        x[0] = a0;
        int n = 0;
        do {
            x[n + 1] = x[n] - f(x[n]) / df(x[n]);
            n++;
        } while(f(x[n]) >= error && Math.abs(x[n] - x[n - 1]) >= error);

        System.out.println("Pasul " + n + ": " + x[n]);
        System.out.println("Pasul " + n + ": " + -x[n]);
    }

    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        System.out.print("Numarul de zecimale: ");

        int noOfDecimalsInError = Integer.parseInt(sc.nextLine());
        double error = 1 / Math.pow(10, noOfDecimalsInError);

        System.out.println();

        System.out.println("---Metoda bisectiei ");
        metodaBisectiei(0.0, 1.0, error);
        metodaBisectiei(-1.0, 0.0, error);
        metodaBisectiei(-2.4, -1.0, error);
        metodaBisectiei(1.0, 2.4, error);

        System.out.println();

        System.out.println("---Regula Falsi ");
        regulaFalsi(0.0, 1.0, error);
        regulaFalsi(-1.0, 0.0, error);
        regulaFalsi(-2.4, -1.0, error);
        regulaFalsi(1.0, 2.4, error);

        System.out.println();

        System.out.println("---Metoda Coardei ");
        metodaCoardei(0.0, 1.0, error);
        metodaCoardei(-1.0, 0.0, error);
        metodaCoardei(-2.4, -1.0, error);
        metodaCoardei(1.0, 2.4, error);

        System.out.println();

        System.out.println("---Metoda Secantei ");
        metodaSecantei(2.219,2.28, error);
        metodaSecantei(-0.9,-0.75, error);

        System.out.println();

        System.out.println("---Metoda Newton ");
        metodaNewton(-1.0, error);
        metodaNewton(-2.4, error);

    }
}
