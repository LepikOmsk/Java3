package Lesson_4;

public class Lesson4_DZ {

    private Object synch = new Object();
    private char cyrrentSymbol = 'A';

    public static void main(String[] args) {
        Lesson4_DZ w = new Lesson4_DZ();

        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        Thread t3 = new Thread(() -> {
            w.printC();
        });
        t1.start();
        t2.start();
        t3.start();

    }

    public void printA () {
        synchronized (synch) {
            try {
                for (int i=0; i < 5; i++){
                    while (cyrrentSymbol != 'A') {
                        synch.wait();
                    }
                    System.out.println("A");
                    cyrrentSymbol = 'B';
                    synch.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB () {
        synchronized (synch) {
            try {
                for (int i=0; i < 5; i++){
                    while (cyrrentSymbol != 'B') {
                        synch.wait();
                    }
                    System.out.println("B");
                    cyrrentSymbol = 'C';
                    synch.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC () {
        synchronized (synch) {
            try {
                for (int i=0; i < 5; i++){
                    while (cyrrentSymbol != 'C') {
                        synch.wait();
                    }
                    System.out.println("C");
                    cyrrentSymbol = 'A';
                    synch.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
