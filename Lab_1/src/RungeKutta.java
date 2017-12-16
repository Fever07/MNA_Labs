public class RungeKutta extends SingleStepMethod {

    private float eps;

    RungeKutta(float a, float b, float h, float eps, float y0) {
        super(a, b, h, y0);
        this.eps = eps;
    }

    private float f(float x, float y) {
        return (float)(-3.3 * Math.pow(x, 2.3) * y * Math.sin(Math.pow(x, 3.3)));
    }

    protected float doIteration(float x, float y, float h) {
        float k1 = h * f(x, y);
        float k2 = h * f((float)(x + h / 2.0), (float)(y + k1 / 2.0));
        float k3 = h * f((float)(x + h / 2.0), (float)(y + k2 / 2.0));
        float k4 = h * f(x + h, y + k3);
        return (float)(y + 1.0 / 6.0 * (k1 + 2 * k2 + 2 * k3 + k4));
    }

    private boolean checkGrid(float a, float b, float h, float y0, float eps) {
        float[] sol1 = this.h == h ? y : calc(h);
        float[] sol2 = this.h == h / 2 ? y : calc(h / 2);
        boolean checkedError = true;
        for (int i = 0; i < sol1.length; i++) {
            if (1.0 / 15.0 * Math.abs(sol1[i] - sol2[2 * i]) > eps) {
                checkedError = false;
                break;
            }
        }
        return checkedError;
    }

    private float calcStepByRungeRule() {
        float hRule = this.h;
        while (!checkGrid(a, b, hRule, y0, eps)) {
            hRule /= 2;
        }
        return hRule;
    }

    public void calc() {
        calc(h);
        float hRule = calcStepByRungeRule();
        calc(hRule);
    }

}
