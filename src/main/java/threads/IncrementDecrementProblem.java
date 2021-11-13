package threads;

public class IncrementDecrementProblem {
    private static int WORKERS = 100000;
    private static class InventoryClass {
        private int items = 0;

        public synchronized void increment() {
            this.items++;
        }

        public synchronized void decrement() {
            this.items--;
        }

        public int getItems() {
            return this.items;
        }
    }

    private static class IncrementingThread extends Thread {
        private InventoryClass inventoryClass;

        public IncrementingThread(InventoryClass inventoryClass) {
            this.inventoryClass = inventoryClass;
        }

        @Override
        public void run() {
            for(int i=0; i<WORKERS; i++) {
                this.inventoryClass.increment();
            }
        }
    }

    private static class DecrementingThread extends Thread {
        private InventoryClass inventoryClass;

        public DecrementingThread(InventoryClass inventoryClass) {
            this.inventoryClass = inventoryClass;
        }

        @Override
        public void run() {
            for(int i=0; i<WORKERS; i++) {
                this.inventoryClass.decrement();
            }
        }
    }

    public static int run() throws InterruptedException {
        InventoryClass inventory = new InventoryClass();
        long startTime = System.currentTimeMillis();
        IncrementingThread incrementingThread = new IncrementingThread(inventory);
        DecrementingThread decrementingThread = new DecrementingThread(inventory);
        incrementingThread.start();
        decrementingThread.start();
        incrementingThread.join();
        decrementingThread.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Inventory.items: " + inventory.getItems());
        System.out.println("Time taken to execute " + (endTime - startTime));
        return inventory.getItems();
    }
}
