class RungeKutta {

    private float a;
    private float b;
    private float h0;
    private float eps;
    private float y0;
    private float hLast = -1;
    private float[] xLast;
    private float[] yLast;
    private int nLast = -1;

    RungeKutta(float a, float b, float h0, float eps, float y0) {
        this.a = a;
        this.b = b;
        this.h0 = h0;
        this.eps = eps;
        this.y0 = y0;
    }

    private float f(float x, float y) {
        return (float)(-3.3 * Math.pow(x, 2.3) * y * Math.sin(Math.pow(x, 3.3)));
    }

    float doIteration(float x, float y, float h) {
        float k1 = h * f(x, y);
        float k2 = h * f((float)(x + h / 2.0), (float)(y + k1 / 2.0));
        float k3 = h * f((float)(x + h / 2.0), (float)(y + k2 / 2.0));
        float k4 = h * f(x + h, y + k3);
        return (float)(y + 1.0 / 6.0 * (k1 + 2 * k2 + 2 * k3 + k4));
    }

    private float[] getApproximateSolution(float h) {
        if (hLast == h) {
            return yLast;
        }
        int n = (int)((b - a) / h) + 1;
        float[] x = new float[n];
        float[] y = new float[n];
        x[0] = a;
        x[n - 1] = b;
        y[0] = y0;
        for (int i = 0; i < n - 1; i++) {
            x[i + 1] = x[i] + h;
            y[i + 1] = doIteration(x[i], y[i], h);
        }
        hLast = h;
        xLast = x;
        yLast = y;
        nLast = n - 1;
        return y;
    }

    private boolean checkGrid(float a, float b, float h, float y0, float eps) {
        float[] sol1 = getApproximateSolution(h);
        float[] sol2 = getApproximateSolution(h / 2);
        boolean checkedError = true;
        for (int i = 0; i < sol1.length; i++) {
            if (1.0 / 15.0 * Math.abs(sol1[i] - sol2[2 * i]) > eps) {
                checkedError = false;
                break;
            }
        }
        return checkedError;
    }

    private float getStepByRungeRule() {
        float h = h0;
        while (!checkGrid(a, b, h, y0, eps)) {
            h /= 2;
        }
        return h;
    }

    void calculateApproximateSolution() {
        float h = getStepByRungeRule();
        getApproximateSolution(h);
    }

    int getN() {
        return nLast;
    }

    float getH() {
        return hLast;
    }

    float[] getY() {
        return yLast;
    }

    float[] getX() {
        return xLast;
    }

}
