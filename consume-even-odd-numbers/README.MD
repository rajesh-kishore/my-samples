Synopsis

This is a program to consume odd even numbers for an array one by one by spanning two or more dedicated Odd Even Threads.
int numbers[] = { 11, 2, 1, 45, 46, 24, 26, 28, 32, 31 };
Odd thread should pick   - 11, 1, 45, 31
Even thread should pick   - 2, 46, 24, 26, 28, 32
Order in which it should be printed should be sequential i.e.
pool-1-thread-1 [ Odd] 11
pool-1-thread-3 [ Even] 2
pool-1-thread-2 [ Odd] 1
pool-1-thread-2 [ Odd] 45
pool-1-thread-3 [ Even] 46
pool-1-thread-3 [ Even] 24
pool-1-thread-3 [ Even] 26
pool-1-thread-3 [ Even] 28
pool-1-thread-3 [ Even] 32
pool-1-thread-1 [ Odd] 31 