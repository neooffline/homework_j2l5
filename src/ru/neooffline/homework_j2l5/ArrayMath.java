package ru.neooffline.homework_j2l5;

public class ArrayMath {
    static final int SIZE = 100000000;
    static final int HALF_SIZE = SIZE / 2;
    float[] arr = new float[SIZE];

    private long currentTime, resultTime;

    public void mathArray(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    + Math.cos(0.5f + i / 2));
        }
    }

    public void oneThreadMath() {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1.0f;
        }
        long currentTime = System.currentTimeMillis();
        mathArray(arr);
        System.out.printf("Время, затраченное без использования разделения массива %d мсек.\n",
                (System.currentTimeMillis() - currentTime));
    }

    public void twoThreadMath() throws InterruptedException {
        float[] initArr = new float[SIZE];
        float[] arr1 = new float[HALF_SIZE];
        float[] arr2 = new float[HALF_SIZE];
        for (int i = 0; i < SIZE; i++) {
            initArr[i] = 1.0f;
        }
        currentTime = System.currentTimeMillis();
        System.arraycopy(initArr, 0, arr1, 0, HALF_SIZE);
        System.arraycopy(initArr, HALF_SIZE, arr2, 0, HALF_SIZE);
        resultTime = System.currentTimeMillis() - currentTime;
        Thread fistThread = new Thread(() -> {
            mathArray(arr1);
        }
        );
        Thread secondThread = new Thread(() -> {
            mathArray(arr2);
        });
        currentTime = System.currentTimeMillis();
        fistThread.start();
        fistThread.join();
        resultTime += System.currentTimeMillis() - currentTime;
        currentTime = System.currentTimeMillis();
        secondThread.start();
        secondThread.join();
        resultTime += System.currentTimeMillis() - currentTime;
        currentTime = System.currentTimeMillis();
        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);
        resultTime += System.currentTimeMillis() - currentTime;
        //Проверка заполнения массива
        for (int i = 0; i < arr.length; i += 1454554) {
            System.out.printf("Элемент номер %d = %.3f\n",
                    i, arr[i]);
        }
        System.out.printf("Время, затраченное c использованием разделения массива %d мсек.\n",
                resultTime);
    }


}
