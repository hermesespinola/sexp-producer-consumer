package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sexp.*;
import sexp.operators.*;

public class Producer extends Thread {
    Buffer<Symbol> buffer;
    private static final String OPERATIONS = "+-/*";
    private final int waitTime, min, max;
    private int nProducts;
    private Random r;
    private boolean running = true;
    static java.util.function.Consumer<Symbol> taskAddedListener;

    Producer(Buffer<Symbol> buffer, int waitTime, int nProducts, int minVal, int maxVal, java.util.function.Consumer<Symbol> taskAddedListener) {
        this.buffer = buffer;
        this.waitTime = waitTime;
        this.nProducts = nProducts;
        this.min = minVal;
        this.max = maxVal;
        this.taskAddedListener = taskAddedListener;
    }
    
    public void terminate() {
        this.running = false;
    }
    
    public void setNProducts(int val) {
        this.nProducts = val;
    }
    
    public Symbol genRandomSexp() {
        int i = r.nextInt(OPERATIONS.length() * 3);
        if (i < OPERATIONS.length()) {
            switch (OPERATIONS.charAt(i)) {
                case '+':
                    return new Sum(genRandomSexp(), genRandomSexp());
                case '-':
                    return new Difference(genRandomSexp(), genRandomSexp());
                case '*':
                    return new Multiplication(genRandomSexp(), genRandomSexp());
                case '/':
                    return new Division(genRandomSexp(), genRandomSexp());
                default:
                    return new SNumber(this.min + r.nextInt(this.max));
            }
        } else {
            int next = this.min + r.nextInt(this.max - this.min);
            return new SNumber(next == 0 ? this.max : next);
        }
    }

    @Override
    public void run() {
        System.out.println("Running Producer (" + this.nProducts + ")...");
        this.r = new Random(System.currentTimeMillis() * this.hashCode());
        Symbol product;

        for(int i = 0 ; i < nProducts; i++) {
            if (!running) {
                break;
            }
            product = genRandomSexp();
            this.buffer.produce(product);
            Buffer.print("Producer produced: " + product);
            this.taskAddedListener.accept(product);
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("End Producer...");
    }

}
