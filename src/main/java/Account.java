
public class Account {


    private int amount1, amount2;
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    public Account(int amount1, int amount2) {
        this.amount1 = amount1;
        this.amount2 = amount2;
    }


    public void GiveFrom1To2(int amount) {
        synchronized (monitor1) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (amount1 >= amount) {

                synchronized (monitor2) {
                    amount1 -= amount;
                    amount2 += amount;
                }
            }else
                System.out.println("Insufficient funds");
        }

    }

    public void GiveFrom2To1(int amount) {
        synchronized (monitor2) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (amount2 >= amount) {

                synchronized (monitor1) {
                    amount1 += amount;
                    amount2 -= amount;
                }
            }else{
                System.out.println("Insufficient funds");
            }
        }
    }
}
