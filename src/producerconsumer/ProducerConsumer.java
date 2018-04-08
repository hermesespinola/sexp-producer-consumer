package producerconsumer;

public final class ProducerConsumer {
    
    private ProducerConsumer() {
        throw new Error();
    }

    private static Consumer[] consumers;
    private static Producer[] producers;
    private static Buffer buffer;
    private static int size, cWait, pWait, productsPerConsumer, productsPerProducer;
    
    public static void init(int bufferSize, int consumerWaitTime, 
            int producerWaitTime, int nConsumers, int nProducers) {
        size = bufferSize;
        cWait = consumerWaitTime;
        pWait = producerWaitTime;
        productsPerProducer = bufferSize / nProducers;
        productsPerConsumer = bufferSize / nConsumers;
        buffer = new Buffer(size, producerWaitTime);
        initProducers(nProducers);
        initConsumers(nConsumers);
    }
    
    public static void initConsumers(int nConsumers) {
        consumers = new Consumer[nConsumers];
        for (int i = 0; i < nConsumers; i++) {
            consumers[i] = new Consumer(buffer, cWait, productsPerConsumer);
        }
        if (productsPerConsumer * nConsumers < size) {
            consumers[nConsumers - 1].setNProducts(productsPerConsumer + 1);
        }
    }
    
    public static void initProducers(int nProducers) {
        producers = new Producer[nProducers];
        for (int i = 0; i < nProducers; i++) {
            producers[i] = new Producer(buffer, pWait, productsPerProducer);
        }
        if (productsPerProducer * nProducers < size) {
            producers[nProducers - 1].setNProducts(productsPerProducer + 1);
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
        int bufferSize = 10;
        int consumerWaitTime = 1000;
        int producerWaitTime = 1000;
        int nConsumers = 3;
        int nProducers = 2;
        init(bufferSize, consumerWaitTime, producerWaitTime, nConsumers, nProducers);
        start();
    }
}
