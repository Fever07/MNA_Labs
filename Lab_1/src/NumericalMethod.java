public abstract class NumericalMethod {

    float a;
    float b;
    float h;
    float y0;
    float[] x;
    float[] y;
    int n;

    NumericalMethod(float a, float b, float h, float y0) {
        this.a = a;
        this.b = b;
        this.h = h;
        this.y0 = y0;
    }

    int calcN(float h) {
        return (int)((b - a) / h) + 1;
    }
    
    float[] calcGrid(float h) {
        int n = calcN(h);
        float[] x = new float[n];
        for (int i = 0; i < n; i++) {
            x[i] = a + i * h;
        }
        return x;
    }

    public abstract void calc();

    float[] getX() {
        return x;
    };

    float[] getY() {
        return y;
    }

    float getH() {
        return h;
    }

    int getN() {
        return n;
    }
}