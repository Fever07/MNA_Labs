public class ImplicitAdams extends NumericalMethod {

    private float y0;
    private float y1;
    private float[] yExp;

    ImplicitAdams(float a, float b, float h, float y0, float y1, float[] yExp) {
        super(a, b, h, y0);
        this.y1 = y1;
        this.yExp = yExp;
    }

    private float f(float x, float y) {
        return (float)(-3.3 * Math.pow(x, 2.3) * y * Math.sin(Math.pow(x, 3.3)));
    }

    public void calc() {
        int n = calcN(h);
        float[] x = calcGrid(h);
        float[] y = new float[n];
        y[0] = y0;
        y[1] = y1;
        for (int i = 1; i < n - 1; i++) {
            y[i + 1] = y[i] + h / 12 * (5 * f(x[i + 1], yExp[i + 1])
                    + 8 * f(x[i], y[i])
                    - f(x[i - 1], y[i - 1]));
        }
        this.x = x;
        this.y = y;
    }

}
