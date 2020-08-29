package com.bupt.juc01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、i++的原子性问题: i++的操作实际上分为三个步骤 “ 读-写-改”
          int i = 10;
          i = i++; // i = 10
       底层实现：
          int temp = i;
          i = i + 1;
          i = temp;
  二、原子变量：jdk1.5以后，java.util.concurrent.atomic 包下提供过了常用的原子变量：
    1.volatile 保证内存可见性
    2.CAS（Compare-And-Swap） 算法保证数据的原子性
        CAS算法是硬件对于并发操作共享数据的支持
        CAS算法包含三个操作数
            内存值V
            预估值A
            更新值B
            当且仅当V == A时，V = B. 否则，将不做任何操作。
 *
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }

    }

}

class AtomicDemo implements Runnable {

//    private int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }
}
