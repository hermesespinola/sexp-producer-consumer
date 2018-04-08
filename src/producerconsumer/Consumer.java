package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import sexp.Symbol;

public class Consumer extends Thread {
    Buffer<Symbol> buffer;
    private final int waitTime;
    private int nProducts;
    
    public void setNProducts(int val) {
        this.nProducts = val;
    }

    Consumer(Buffer buffer, int waitTime, int nProducts) {
        this.buffer = buffer;
        this.waitTime = waitTime;
        this.nProducts = nProducts;
    }

    @Override
    public void run() {
        System.out.println("Running Consumer (" + this.nProducts + ")...");
        Symbol product;

        for(int i = 0; i < this.nProducts; i++) {
            product = this.buffer.consume();
            Buffer.print("Consumer evaluated: " + product.eval());

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("End Consumer...");
    }
}
