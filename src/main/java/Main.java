import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void SinglePool(){
        long before = System.currentTimeMillis();
        ExecutorService executorServiceSinglePool = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch1 = new CountDownLatch(3);

        executorServiceSinglePool.execute(new Runnable() {
            @Override
            public void run() {
                long sum22 = 0;
                for (int i = 0; i < 1_000_000; i++){
                    if (i % 2 == 0){
                        sum22 += i;
                    }
                }
                System.out.println("1.Suma: " + sum22);
                countDownLatch1.countDown();
            }
        });

        executorServiceSinglePool.execute(new Runnable() {
            @Override
            public void run() {
                long sum33 = 0;
                for (int i = 0; i < 1_000_000; i++){
                    if (i % 7 == 0){
                        sum33 += i;
                    }
                }
                System.out.println("2.Suma: " + sum33);
                countDownLatch1.countDown();
            }
        });

        executorServiceSinglePool.execute(new Runnable() {
            @Override
            public void run() {
                List<Integer> qwestList = new ArrayList();
                int r;
                int count33 = 0;
                for (int i = 0; i < 1000; i++){
                    qwestList.add((int)(Math.random()*1000));
                }
                for( int x : qwestList){
                    if (x % 2 == 0){
                        count33++;
                    }
                }
                System.out.println("3. Count - " + count33);
                countDownLatch1.countDown();
            }
        });
        executorServiceSinglePool.shutdown();
        try {
            countDownLatch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long after = System.currentTimeMillis();
        System.out.println("Time FixedPool: " + (after - before));
        System.out.println("Finished Single Pool.");

    }

    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        ExecutorService executorServiceFixedPool = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        executorServiceFixedPool.execute(new Runnable() {
            @Override
            public void run() {
                long sum1 = 0;
                for (int i = 0; i < 1000000; i++){
                    if (i % 2 == 0){
                        sum1 += i;
                    }
                }
                System.out.println("1.Suma: " + sum1);
                countDownLatch.countDown();
            }
        });

        executorServiceFixedPool.execute(new Runnable() {
            @Override
            public void run() {
                long sum = 0;
                for (int i = 0; i < 1000000; i++){
                    if (i % 7 == 0){
                        sum += i;
                    }
                }
                System.out.println("2.Suma: " + sum);
                countDownLatch.countDown();
            }
        });

        executorServiceFixedPool.execute(new Runnable() {
            @Override
            public void run() {
                List<Integer> qwestList = new ArrayList<>();

                int count = 0;
                for (int i = 0; i < 1000; i++){

                    qwestList.add((int)(Math.random()*1000));
                }

                for( int x : qwestList){
                    if (x % 2 == 0){
                        count++;
                    }
                }
                System.out.println("3. Count - " + count);
                countDownLatch.countDown();
            }
        });
        executorServiceFixedPool.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.currentTimeMillis();
        System.out.println("Finished Fixed Pool.");
        System.out.println("Time FixedPool: " + (after - before));
       SinglePool();

    }

}


