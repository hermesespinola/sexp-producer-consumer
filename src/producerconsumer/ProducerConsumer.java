package producerconsumer;

import sexp.Symbol;

public final class ProducerConsumer {

    private ProducerConsumer() {
        throw new Error();
    }

    private static Consumer[] consumers;
    private static Producer[] producers;
    private static Buffer<Symbol> buffer;
    private static int size, cWait, pWait, productsPerConsumer, productsPerProducer;
    private static int min, max;

    public static void init(int bufferSize, int consumerWaitTime, 
            int producerWaitTime, int nConsumers, int nProducers, int minVal, int maxVal) {
        size = bufferSize;
        cWait = consumerWaitTime;
        pWait = producerWaitTime;
        productsPerProducer = bufferSize / nProducers;
        productsPerConsumer = bufferSize / nConsumers;
        buffer = new Buffer(size, producerWaitTime);
        min = minVal;
        max = maxVal;
        initProducers(nProducers);
        initConsumers(nConsumers);
    }

    public static void initConsumers(int nConsumers) {
        consumers = new Consumer[nConsumers];
        for (int i = 0; i < nConsumers; i++) {
            consumers[i] = new Consumer(buffer, cWait, productsPerConsumer);
        }
        int missing = size - productsPerConsumer * nConsumers;
        for (int i = 0; i < missing; i++) {
            if (i < nConsumers) {
                consumers[i].setNProducts(productsPerConsumer + 1);
            }
        }
    }

    public static void initProducers(int nProducers) {
        producers = new Producer[nProducers];
        for (int i = 0; i < nProducers; i++) {
            producers[i] = new Producer(buffer, pWait, productsPerProducer, min, max);
        }
        int missing = size - productsPerProducer * nProducers;
        for (int i = 0; i < missing; i++) {
            if (i < nProducers) {
                producers[i].setNProducts(productsPerProducer + 1);
            }
        }
    }

    public static void start() {
        for (Producer producer : producers) {
            producer.start();
        }
        
        for (Consumer consumer : consumers) {
            consumer.start();
        }
    }

    public void main(String ...args) {
        int bufferSize = 1;
        int consumerWaitTime = 1000;
        int producerWaitTime = 1000;
        int nConsumers = 1;
        int nProducers = 1;
        int minVal = 2;
        int maxVal = 45;
        init(bufferSize, consumerWaitTime, producerWaitTime, nConsumers, nProducers, minVal, maxVal);
        start();
    }
}
