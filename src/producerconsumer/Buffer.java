package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer {
    
    private final LinkedBlockingQueue<Character> buffer;
    private final int bufferMaxSize, producerWaitTime;
    private int bufferSize;
    
    Buffer(int size, int producerWaitTime) {
        this.buffer = new LinkedBlockingQueue();
        this.producerWaitTime = producerWaitTime;
        this.bufferMaxSize = size;
        this.bufferSize = 0;
    }
    
    char consume() {
        char product;
        try {
            product = this.buffer.take();
        } catch (InterruptedException e) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            product = 0;
        }
        return product;
    }
    
    void produce(char product) {
        if (bufferSize < bufferMaxSize) {
            this.buffer.add(product);
            bufferSize += 1;
        }
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
