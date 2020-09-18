import java.util.concurrent.locks.*;

// Semaphore is a generalization of mutual exclusion locks. It allows at most N threads into critical section. Java has a built-in Semaphore.
// times by the same thread. Simple Reentrant Lock
// uses owner thread ID and hold count fields to keep
// track of the owning thread and the number of times
// it holds the lock. A common lock is used for
// ensuring field updates are atomic, and a condition
// object is used for synchronization.
// cr
// Acquiring the lock involves holding the common
// lock, waiting until there is no other thread
// holding it, updating owner thread ID (to current)
// and incrementing hold count before releasing the
// common lock.
// 
// Releasing the write lock involves holding the
// common lock, decrementing hold count, and if
// not holding anymore, signalling the others before
// releasing the common lock.
// 
// Java already provides a ReentrantLock. This is
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
