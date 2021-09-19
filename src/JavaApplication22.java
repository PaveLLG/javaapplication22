import static javafx.scene.input.KeyCode.T;

public class JavaApplication22 {

    static Monitor mon = new Monitor();

    public static void main(String[] args) throws InterruptedException {



        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i < 100; i++ ) {
                    System.out.println(" 1 - " + i);
                    if(i >= 49){
                        synchronized (mon){
                            mon.x = i;
                            mon.notify();
                        }
                    }

                }

            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (mon) {
                        while (mon.x < 49) mon.wait();
                    }
                }catch (InterruptedException e){}
                for(int i = 0; i < 100; i++ )
                    System.out.println(" 2 - " + i );

            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();



    }


}

class Monitor {
    int x;
}
