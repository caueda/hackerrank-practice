package threads;

public class DataRaceCondition {
    private static class SharedObject {
        private volatile long x;
        private volatile long y;

        public void increment() {
            x++;
            y++;
        }

        public long getX() {
            return this.x;
        }

        public long getY() {
            return this.y;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }

    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        Thread thread1 = new Thread(() -> {
            for (int i=0; i<Long.MAX_VALUE; i++) {
                sharedObject.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i=0; i<Long.MAX_VALUE; i++) {
                sharedObject.checkForDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
