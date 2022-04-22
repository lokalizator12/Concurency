public class MFU {

    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    public void print(int count){
        synchronized (monitor1) {
            try {
                for (int i = 1; i <= count; i++) {

                    System.out.println("Отпечатано " + i + " стр.");
                    Thread.sleep(2000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void scan(int count) {
        synchronized (monitor2) {
            try {

                for (int i = 1; i <= count; i++) {
                    System.out.println("Отсканировано " + i + " стр.");
                    Thread.sleep(2000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
