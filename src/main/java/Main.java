import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int size = 50_000_000;
    public static final int half = size / 2;

    public static void main(String[] args) {

       // startTimer();
       // withoutConcurrency();
       // with();


        giveMeMoney();


    }

    private static void giveMeMoney(){
        Bankomat profile1 = new Bankomat(1000);

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {

                profile1.ifHaveMoney("kol",500);
            }
        });

        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {

            profile1.ifHaveMoney("uit",300);
            }
        });

        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {

                profile1.ifHaveMoney("uidst",600);
            }
        });
        thread4.start();

        try {
            thread4.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread5.start();
        thread6.start();


    }

    private static void withoutConcurrency() {
        float[] list1 = new float[size];
        for (int i = 0; i < list1.length; i++) {
            list1[i] = 1f;
        }
        long before = System.currentTimeMillis();
        for (int i = 0; i < list1.length; i++) {
            float f = (float) i;
            list1[i] = (float) (list1[i] * Math.sin(0.2f + f / 5) * Math.cos(0.4f + f / 2));
        }
        long after = System.currentTimeMillis();
        System.out.println("without :" + (after - before));
    }

    private static void with() {
        float[] list1 = new float[size];

        for (int i = 0; i < list1.length; i++) {
            list1[i] = 1f;
        }

        long before1 = System.currentTimeMillis();
        float[] list2 = new float[half];
        float[] list3 = new float[half];
        System.arraycopy(list1, 0, list2, 0, half);
        System.arraycopy(list1, half, list3, 0, half);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long before = System.currentTimeMillis();
                for (int i = 0; i < half; i++) {
                    float f = (float) (i + half);
                    list2[i] = (float) (list2[i] * Math.sin(0.2f + f / 5) * Math.cos(0.4f + f / 2));
                }

                long after = System.currentTimeMillis();
                System.out.println("arrayCopy1 :" + (after - before));
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long before = System.currentTimeMillis();
                for (int i = 0; i < list3.length; i++) {
                    float f = (float) (i + half);
                    list3[i] = (float) (list3[i] * Math.sin(0.2f + f / 5) * Math.cos(0.4f + 3 / 2));
                }

                long after = System.currentTimeMillis();
                System.out.println("arrayCopy2 :" + (after - before));
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(list2, 0, list1, 0, list2.length);
        System.arraycopy(list3, 0, list1, half, list3.length);
        long after = System.currentTimeMillis();
        System.out.println("time with :" + (after - before1));
    }

    public static void startTimer() {

        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                int second4 = 0;
                while (true){
                    System.out.println(second4++);
                }

            }
        });
        timer.start();
        timer.setDaemon(true);
    }




}


