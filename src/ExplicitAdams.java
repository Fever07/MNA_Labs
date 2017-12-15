public class ExplicitAdams {

    private float a;
    private float b;
    private float h;
    private float y0;
    private float y1;
    private float y2;
    private float[] x;
    private float[] y;

    ExplicitAdams(float a, float b, float h, float y0, float y1, float y2) {
        this.a = a;
        this.b = b;
        this.h = h;
        this.y0 = y0;
        this.y1 = y1;
        this.y2 = y2;
    }

    private float f(float x, float y) {
        return (float)(-3.3 * Math.pow(x, 2.3) * y * Math.sin(Math.pow(x, 3.3)));
    }

    void calculateApproximateSolution() {
        int n = (int)((b - a) / h + 1);
        float[] x = new float[n];
        float[] y = new float[n];
        for (int i = 0; i < n; i++) {
            x[i] = a + i * h;
        }
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
    }

    public float[] getX() {
        return x;
    }

    public float[] getY() {
        return y;
    }
}
