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

    Producer(Buffer<Symbol> buffer, int waitTime, int nProducts, int minVal, int maxVal) {
        this.buffer = buffer;
        this.waitTime = waitTime;
        this.nProducts = nProducts;
        this.min = minVal;
        this.max = maxVal;
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
            return new SNumber(this.min + r.nextInt(this.max));
        }
    }

    @Override
    public void run() {
        System.out.println("Running Producer (" + this.nProducts + ")...");
        this.r = new Random(System.currentTimeMillis() * this.hashCode());
        Symbol product;

        for(int i = 0 ; i < nProducts; i++) {
            product = genRandomSexp();
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
