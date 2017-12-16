import java.lang.Math;

public class Main {

    static void printArray(float[] vec) {
        for (float elem : vec) {
            System.out.print(Float.toString(elem) + " ");
        }
        System.out.println();
    }

    static float u(float x) {
        return (float)Math.exp(Math.cos(Math.pow(x, 3.3)));
    }

    public static void main(String[] args) {
        float a = 0;
        float b = 1.2f;
        float h0 = 0.2f;
        float eps= 1e-3f;
        float y0 = (float)Math.E; // from exact solution

        // task 1
        RungeKutta rk = new RungeKutta(a, b, h0, eps, y0);
        rk.calc();

        float[] x = rk.getX();
        float[] y = rk.getY();
        int n = rk.getN();
        float h = rk.getH();
        float eG = -1f;
        float[] errs = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            errs[i] = Math.abs(u(x[i]) - y[i]);
            eG = Math.max(eG, errs[i]);
        }
        System.out.println(n);
        System.out.println(eG);
        System.out.println(h);
        printArray(y);
        printArray(errs);

        // task 2
        float h2 = 5e-2f;
        ExplicitAdams ea = new ExplicitAdams(a, b, h2, y0, y[1], y[2]);
        ea.calc();
        float[] xa = ea.getX();
        float[] ya = ea.getY();
        printArray(ya);
        ImplicitAdams ia = new ImplicitAdams(a, b, h2, y0, y[1], ya);
        ia.calc();
        float[] yia = ia.getY();
        printArray(yia);
        float eGa = -1f;
        for (int i = 0; i < x.length; i++) {
            eGa = Math.max(eGa, Math.abs(u(xa[i]) - yia[i]));
        }
        System.out.println(eGa);

        // task 3
        DormandPrince dp = new DormandPrince(a, b, h0, y0);
        dp.calc();
        float[] xdp = dp.getX();
        float[] ydp = dp.getY();
        printArray(ydp);
        float eGdp = -1f;
        for (int i = 0; i < x.length; i++) {
            eGdp = Math.max(eGdp, Math.abs(u(xdp[i]) - ydp[i]));
        }
        System.out.println(eGdp);
    }
}
