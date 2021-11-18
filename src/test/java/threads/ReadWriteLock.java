package threads;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    public static final int HIGHEST_PRICE = 1000;
    public static void main(String[] args) throws InterruptedException {
        InventoryDatabase inventoryDatabase = new InventoryDatabase();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
        }

        Thread writer = new Thread(()-> {
            while(true) {
                inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
                inventoryDatabase.removeItem(random.nextInt(HIGHEST_PRICE));
                try {
                    Thread.sleep(10);
                } catch(InterruptedException e) {
                }
            }
        });
        writer.setDaemon(true);
        writer.start();

        int numberOfReadersThreads = 7;
        List<Thread> readers = new ArrayList<>();
        for(int readerIndex=0; readerIndex < numberOfReadersThreads; readerIndex++) {
            Thread reader = new Thread(()->{
                for(int i=0; i<100000; i++) {
                    int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
                    int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;
                    inventoryDatabase.getNumberOfItemsInPriceRange(lowerBoundPrice, upperBoundPrice);
                }
            });
            reader.setDaemon(true);
            readers.add(reader);
        }

        long startReadingTime = System.currentTimeMillis();
        for(Thread reader: readers) {
            reader.start();
        }
        for(Thread reader: readers) {
            reader.join();
        }
        long endReadingTime = System.currentTimeMillis();
        System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
    }

    public static class InventoryDatabase {
        private TreeMap<Integer, Integer> priceToCountMap = new TreeMap();
        private ReentrantLock lock = new ReentrantLock();
        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        private Lock writeLock = rwLock.writeLock();
        private Lock readLock = rwLock.readLock();

        public int getNumberOfItemsInPriceRange(int lowerBound, int upperBound) {
//            lock.lock();
            readLock.lock();
            try {
                Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
                Integer toKey = priceToCountMap.floorKey(upperBound);
                if(fromKey == null || toKey == null) {
                    return 0;
                }
                NavigableMap<Integer, Integer> rangeOfPrices =
                        priceToCountMap.subMap(fromKey, true, toKey, true);
                int sum = 0;
                for(int numberOfItemsForPrice: rangeOfPrices.values()) {
                    sum += numberOfItemsForPrice;
                }
                return sum;
            } finally {
//                lock.unlock();
                readLock.unlock();
            }
        }

        public void addItem(int price) {
//            lock.lock();
            writeLock.lock();
            try {
                Integer numberOfItemForPrice = priceToCountMap.get(price);
                if(numberOfItemForPrice == null) {
                    priceToCountMap.put(price, 1);
                } else {
                    priceToCountMap.put(price, numberOfItemForPrice + 1);
                }
            } finally {
//                lock.unlock();
                writeLock.unlock();
            }
        }

        public void removeItem(int price) {
//            lock.lock();
            writeLock.lock();
            try {
                Integer numberOfItemForPrice = priceToCountMap.get(price);
                if(numberOfItemForPrice == null || numberOfItemForPrice == 1) {
                    priceToCountMap.remove(price);
                } else {
                    priceToCountMap.put(price, priceToCountMap.get(price) - 1);
                }
            } finally {
//                lock.unlock();
                writeLock.unlock();
            }
        }
    }
}
