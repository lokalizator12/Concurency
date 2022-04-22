import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {


        MFU mfu = new MFU();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print(3);
                mfu.scan(3);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print(5);
                mfu.scan(5);
            }
        }).start();
    }

}


