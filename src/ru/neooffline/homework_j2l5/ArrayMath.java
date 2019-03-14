package ru.neooffline.homework_j2l5;

public class ArrayMath extends Thread {
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public void oneTreadMath() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    + Math.cos(0.5f + i / 2));
        }
        System.out.printf("Время, затраченное без использования разделения массива %d мсек.\n",(System.currentTimeMillis() - a));
    }

    public void twoTheadmath() {
        long a,aRes;
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        aRes = System.currentTimeMillis() - a;
        a = System.currentTimeMillis();
        for (int i = 0; i < h; i++) {
            arr1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    + Math.cos(0.5f + i / 2));
        }
        aRes += System.currentTimeMillis() - a;
        a = System.currentTimeMillis();
        for (int i = 0; i < h; i++) {
            arr1[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    + Math.cos(0.5f + i / 2));
        }
        aRes += System.currentTimeMillis() - a;
        a = System.currentTimeMillis();
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        aRes += System.currentTimeMillis() - a;
        System.out.printf("Время, затраченное c использованием разделения массива %d мсек.\n",aRes);
    }


}
