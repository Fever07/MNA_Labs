public abstract class SingleStepMethod extends NumericalMethod{

    SingleStepMethod(float a, float b, float h, float y0) {
        super(a, b, h, y0);
    }

    protected abstract float doIteration(float x, float y, float h);

    float[] calc(float h) {
        int n = calcN(h);
        float[] x = calcGrid(h);
        float[] y = new float[n];
        x[0] = a;
        x[n - 1] = b;
        y[0] = y0;
        for (int i = 0; i < n - 1; i++) {
            x[i + 1] = x[i] + h;
            y[i + 1] = doIteration(x[i], y[i], h);
        }
        this.h = h;
        this.x = x;
        this.y = y;
        this.n = n - 1;
        return y;
    }

}
