package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import sexp.Symbol;

public class Consumer extends Thread {
    Buffer<Symbol> buffer;
    private final int waitTime;
    private int nProducts;
    private java.util.function.Consumer<Pair<String, Double>> finishListener;
    private boolean running = true;
    
    public void terminate() {
        this.running = false;
    }
    
    public void setNProducts(int val) {
        this.nProducts = val;
    }

    Consumer(Buffer buffer, int waitTime, int nProducts, java.util.function.Consumer<Pair<String, Double>> finishListener) {
        this.buffer = buffer;
        this.waitTime = waitTime;
        this.nProducts = nProducts;
        this.finishListener = finishListener;
    }

    @Override
    synchronized public void run() {
        System.out.println("Running Consumer (" + this.nProducts + ")...");
        Symbol product;

        for(int i = 0; i < this.nProducts; i++) {
            if (!running) {
                break;
            }
            product = this.buffer.consume();
            Buffer.print("Consumer evaluated: " + product.eval());
            finishListener.accept(new Pair(product.toString(), product.eval()));

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("End Consumer...");
    }
}
