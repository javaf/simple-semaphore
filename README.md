Semaphore is a generalization of mutual
exclusion locks. It allows at most N threads
into critical section. Simple Semaphore uses
a state field to keep track of the number of
threads currently in CS, and is limited to
a fixed capacity. A common lock is used for
ensuring field updates are atomic, and a
condition object is used for synchronization.

Acquiring the semaphore involves holding the
common lock, waiting until the CS is not full,
and incrementing the number of threads in CS
before releasing the common lock.

Releasing the semaphore involves holding the
common lock, decrementing the number of
threads in CS, and signalling that CS is not
full before releasing the common lock.

Java already provides a Semaphore. This is
for educational purposes only.

> **Course**: [Concurrent Data Structures], Monsoon 2020\
> **Taught by**: Prof. Govindarajulu Regeti

[Concurrent Data Structures]: https://github.com/iiithf/concurrent-data-structures

```java
acquire():
1. Acquire common lock.
2. Wait until CS is not full.
3. Increment number of threads in CS.
4. Release common lock.
```

```java
release():
1. Acquire common lock.
2. Decrement number of threads in CS.
3. Signal that CS is not full.
4. Release common lock.
```

See [SimpleSemaphore.java] for code, [Main.java] for test, and [repl.it] for output.

[SimpleSemaphore.java]: https://repl.it/@wolfram77/simple-semaphore#SimpleSemaphore.java
[Main.java]: https://repl.it/@wolfram77/simple-semaphore#Main.java
[repl.it]: https://simple-semaphore.wolfram77.repl.run


### references

- [The Art of Multiprocessor Programming :: Maurice Herlihy, Nir Shavit](https://dl.acm.org/doi/book/10.5555/2385452)
