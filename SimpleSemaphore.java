import java.util.concurrent.locks.*;

// Semaphore is a generalization of mutual
// exclusion locks. It allows at most N threads
// into critical section. Simple Semaphore uses
// a state field to keep track of the number of
// threads currently in CS, and is limited to
// a fixed capacity. A common lock is used for
// ensuring field updates are atomic, and a
// condition object is used for synchronization.
// 
// Acquiring the semaphore involves holding the
// common lock, waiting until the CS is not full,
// and incrementing the number of threads in CS
// before releasing the common lock.
// 
// Releasing the semaphore involves holding the
// common lock, decrementing the number of
// threads in CS, and signalling that CS is not
// full before releasing the common lock.
// 
// Java already provides a Semaphore. This is
// for educational purposes only.

class SimpleSemaphore {
  final Lock lock;
  final Condition notFull;
  final int capacity;
  int state;
  // lock: common lock
  // notFull: condition for CS "not full"
  // capacity: maximum concurrent threads in CS
  // state: number of threads in CS

  public SimpleSemaphore(int cap) {
    lock = new ReentrantLock();
    notFull = lock.newCondition();
    capacity = cap;
  }

  // 1. Acquire common lock.
  // 2. Wait until CS is not full.
  // 3. Increment number of threads in CS.
  // 4. Release common lock.
  public void acquire() {
    lock.lock(); // 1
    try {
      while (state == capacity) // 2
        notFull.await();        // 2
      state++; // 3
    }
    catch (InterruptedException e) {}
    finally { lock.unlock(); } // 4
  }

  // 1. Acquire common lock.
  // 2. Decrement number of threads in CS.
  // 3. Signal that CS is not full.
  // 4. Release common lock.
  public void release() {
    lock.lock(); // 1
    state--;     // 2
    notFull.signal(); // 3
    lock.unlock();    // 4
  }
}
