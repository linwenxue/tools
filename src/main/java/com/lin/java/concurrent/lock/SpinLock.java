package com.lin.java.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁是指当一个线程尝试获取某个锁时，如果该锁已被其他线程占用，
 * 就一直循环检测锁是否被释放，而不是进入线程挂起或睡眠状态。
 * 自旋锁适用于锁保护的临界区很小的情况，临界区很小的话，锁占用的时间就很短。
 * 缺点：
 * 1. CAS操作需要硬件的配合；
 * 2. 保证各个CPU的缓存（L1、L2、L3、跨CPU Socket、主存）的数据一致性，通讯开销很大，在多处理器系统上更严重；
 * 3. 没法保证公平性，不保证等待进程/线程按照FIFO顺序获得锁。
 * Created by wenxuelin on 2017/7/4.
 */
public class SpinLock {
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        // 如果锁未被占用，则设置当前线程为锁的拥有者
        while(owner.compareAndSet(null, currentThread)) {
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        // 只有锁的拥有者才能释放锁
        owner.compareAndSet(currentThread, null);
    }
}
