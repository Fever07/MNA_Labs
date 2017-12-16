public class ExplicitAdams extends NumericalMethod {

    private float y0;
    private float y1;
    private float y2;

    ExplicitAdams(float a, float b, float h, float y0, float y1, float y2) {
        super(a, b, h, y0);
        this.y1 = y1;
        this.y2 = y2;
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
        y[2] = y2;
        for (int i = 2; i < n - 1; i++) {
            y[i + 1] = y[i] + h / 12 * (23 * f(x[i], y[i])
                    - 16 * f(x[i - 1], y[i - 1])
                    + 5 * f(x[i - 2], y[i - 2]));
        }
        this.x = x;
        this.y = y;
        this.n = n;
    }

}
