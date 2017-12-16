public class DormandPrince extends SingleStepMethod {

    DormandPrince(float a, float b, float h, float y0) {
        super(a, b, h, y0);
    }

    private float f(float x, float y) {
        return (float)(-3.3 * Math.pow(x, 2.3) * y * Math.sin(Math.pow(x, 3.3)));
    }

    protected float doIteration(float x, float y, float h) {
        float k1 = h * f(x, y);
        float k2 = h * f((float)(x + 1.0 / 5.0 * h), (float)(y + 1.0 / 5.0 * k1));
        float k3 = h * f((float)(x + 3.0 / 10.0 * h), (float)(y + 3.0 / 40.0 * k1
                                                                + 9.0 / 40.0 * k2));
        float k4 = h * f((float)(x + 4.0 / 5.0 * h), (float)(y + 44.0 / 45.0 * k1
                                                                - 56.0 / 15.0 * k2
                                                                + 32.0 / 9.0 * k3));
        float k5 = h * f((float)(x + 8.0 / 9.0 * h), (float)(y + 19372.0 / 6561.0 * k1
                                                                - 25360.0 / 2187.0 * k2
                                                                + 64448.0 / 6561.0 * k3
                                                                - 212.0 / 729.0 * k4));
        float k6 = h * f((float)(x + h), (float)(y + 9017.0 / 3168.0 * k1
                                                    - 355.0 / 33.0 * k2
                                                    + 46732.0 / 5247.0 * k3
                                                    + 49.0 / 176.0 * k4
                                                    - 5103.0 / 18656.0 * k5));
        float k7 = h * f((float)(x + h), (float)(y + 35.0 / 384.0 * k1
                                                    + 500.0 / 1113.0 * k3
                                                    + 125.0 / 192.0	* k4
                                                    - 2187.0 / 6784.0 * k5
                                                    + 11.0 / 84.0 * k6));
        return (float)(y + 5179.0 / 57600.0 * k1
                + 7571.0 / 16695.0 * k3
                + 393.0 / 640.0 * k4
                - 92097.0 / 339200.0 * k5
                + 187.0 / 2100.0 * k6
                + 1.0 / 40.0 * k7);
    }

    public void calc() {
        calc(h);
    }

}
