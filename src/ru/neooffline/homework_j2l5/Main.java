package ru.neooffline.homework_j2l5;

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException{
	ArrayMath arrayMath = new ArrayMath();
	arrayMath.oneThreadMath();
	arrayMath.twoThreadMath();
    }
}
