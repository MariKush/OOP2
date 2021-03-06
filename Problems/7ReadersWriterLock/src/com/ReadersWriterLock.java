package com;

import java.util.concurrent.atomic.AtomicInteger;

class ReadersWriterLock {

    AtomicInteger writerReaders = new AtomicInteger(0);

    public static final AtomicInteger WRITER_BIT = new AtomicInteger(1 << 30);

    public ReadersWriterLock() {}

    public AtomicInteger getWriterReaders() {
        return writerReaders;
    }

    public synchronized void startRead() {
        if(writerReaders.incrementAndGet() >= WRITER_BIT.get()) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
    }

    public synchronized void startWrite() {
        while( writerReaders.compareAndSet(0, WRITER_BIT.get() ) != true ) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
    }

    public void endRead() {
        writerReaders.decrementAndGet();
    }

    public synchronized void endWrite() {
        writerReaders.set(writerReaders.get() - WRITER_BIT.get());
        notifyAll();
    }

};