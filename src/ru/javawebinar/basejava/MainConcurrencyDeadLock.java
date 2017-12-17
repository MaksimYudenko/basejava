package ru.javawebinar.basejava;

public class MainConcurrencyDeadLock {
    private volatile int counter;
    private final MainConcurrency mc1 = new MainConcurrency();
    private final MainConcurrency mc2 = new MainConcurrency();

    private Thread thread1 = new Thread(() -> {
        while (true) {
            synchronized (mc1) {
                synchronized (mc2) {
                    counter++;
                }
            }
        }
    });

    private Thread thread2 = new Thread(() -> {
        while (true) {
            synchronized (mc2) {
                synchronized (mc1) {
                    counter++;
                }
            }
        }
    });


    public static void main(String[] args) throws InterruptedException {
        MainConcurrencyDeadLock mdl = new MainConcurrencyDeadLock();
        mdl.thread1.start();
        System.out.println(mdl.counter);
        mdl.thread2.start();
    }
}