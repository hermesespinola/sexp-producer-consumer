package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    private final int waitTime;
    private int nProducts;

    Producer(Buffer buffer, int waitTime, int nProducts) {
        this.buffer = buffer;
        this.waitTime = waitTime;
        this.nProducts = nProducts;
    }
    
    public void setNProducts(int val) {
        this.nProducts = val;
    }

    @Override
    public void run() {
        System.out.println("Running Producer (" + this.nProducts + ")...");
        String products = "QWERTYUIOPASDFGHJKLZXCVBNM";
        Random r = new Random(this.hashCode());
        char product;

        for(int i = 0 ; i < nProducts; i++) {
            product = products.charAt(r.nextInt(products.length()));
            this.buffer.produce(product);
            Buffer.print("Producer produced: " + product);
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("End Producer...");
    }

}
