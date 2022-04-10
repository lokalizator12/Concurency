import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static String nextLetter = "A";
    static Object MONITER = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (MONITER) {
                    for (int i = 0; i < 5; i++) {
                        while (!nextLetter.equals(A)) {
                            try {
                                MONITER.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        System.out.print(A);
                        nextLetter = B;
                        MONITER.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITER) {
                    for (int i = 0; i < 5; i++) {
                        while (!nextLetter.equals(B)) {
                            try {
                                MONITER.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        System.out.print(B);
                        nextLetter = C;
                        MONITER.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITER) {
                    for (int i = 0; i < 5; i++) {
                        while (!nextLetter.equals(C)) {
                            try {
                                MONITER.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        System.out.print(C);
                        nextLetter = A;
                        MONITER.notifyAll();
                    }
                }
            }
        }).start();

    }
}


