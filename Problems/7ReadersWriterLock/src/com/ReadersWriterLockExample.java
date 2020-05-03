package com;

public class ReadersWriterLockExample implements Runnable {
    Integer val;
    ReadersWriterLock lock = new ReadersWriterLock();

    private final int WRITERS_NUMBER = 100;
    private final int READERS_NUMBER = 10;

    public void run() {

        val = new Integer(0);

        WriterThread[] writers = new WriterThread[WRITERS_NUMBER];

        for (int i = 0; i < WRITERS_NUMBER; ++i)
            writers[i] = new WriterThread(i);

        ReaderThread[] readers = new ReaderThread[READERS_NUMBER];

        for (int i = 0; i < READERS_NUMBER; ++i)
            readers[i] = new ReaderThread();

        for (int i = 0; i < WRITERS_NUMBER; ++i) {
            writers[i].run();
            for (int j = 0; j < READERS_NUMBER; ++j)
                readers[j].run();
        }
    }

    public class WriterThread extends Thread {

        int whatToWrite;

        WriterThread(int whatToWrite) {
            this.whatToWrite = whatToWrite;
        }

        @Override
        public void run() {
            lock.startWrite();
            val = new Integer(whatToWrite);
            lock.endWrite();
            System.out.println("\nWrite " + whatToWrite);
        }
    }

    public class ReaderThread extends Thread {

        public int whereToRead;

        ReaderThread() {}

        @Override
        public void run() {
            lock.startRead();
            whereToRead = val.intValue();
            System.out.print("  Read " + whereToRead);
            lock.endRead();
        }
    }
}
