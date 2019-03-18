package ru.neooffline.homework_j2l5;

public class ArrayMath {
    static final int SIZE = 100000000;
    static final int HALF_SIZE = SIZE / 2;
    float[] arr = new float[SIZE];

    private long operationStartTime;

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
        System.out.printf("Время, затраченное без использования разделения массива %d сек.\n",
                (System.currentTimeMillis() - currentTime) / 1000);
    }

    public void twoThreadMath() throws InterruptedException {
        float[] initArr = new float[SIZE];
        float[] arr1 = new float[HALF_SIZE];
        float[] arr2 = new float[HALF_SIZE];
        for (int i = 0; i < SIZE; i++) {
            initArr[i] = 1.0f;
        }
        operationStartTime = System.currentTimeMillis();
        System.arraycopy(initArr, 0, arr1, 0, HALF_SIZE);
        System.arraycopy(initArr, HALF_SIZE, arr2, 0, HALF_SIZE);
        long resultTime = System.currentTimeMillis() - operationStartTime;
        Thread fistThread = new Thread(() -> {
            System.out.println(Thread.currentThread() + " started");
            mathArray(arr1);
        });
        Thread secondThread = new Thread(() -> {
            System.out.println(Thread.currentThread() + " started");
            mathArray(arr2);
        });
        operationStartTime = System.currentTimeMillis();
        fistThread.start();
        secondThread.start();
        fistThread.join();
        secondThread.join();
        resultTime += System.currentTimeMillis() - operationStartTime;
        operationStartTime = System.currentTimeMillis();
        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);
        resultTime += System.currentTimeMillis() - operationStartTime;
        //Проверка заполнения массива
        /*for (int i = 0; i < arr.length; i += 1454554) {
            System.out.printf("Элемент номер %d = %.3f\n",
                    i, arr[i]);
        }*/
        System.out.printf("Время, затраченное c использованием разделения массива %d сек.\n",
                resultTime / 1000);
    }


}
