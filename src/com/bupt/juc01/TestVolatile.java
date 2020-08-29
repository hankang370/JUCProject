package com.bupt.juc01;

/*
  一、volatile关键字：当多个线程进行操作共享数据时，可以保证内存中的数据是可见的。
                    相较于 sychronized 是一种较为轻量级的同步策略
   注意：
   1.volatile 不具备“互斥性”，而sychronized是互斥的
   2.volatile 不能保证变量的“原子性”
 */
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true){
            if(td.isFlag()){
                System.out.println("--------------------");
                break;
            }

        }
    }


}

class ThreadDemo implements Runnable {

    private volatile boolean flag = false;
    // flag是共享数据,存放于主存（堆内存中），
    // 两个线程共同操作该数据，但是是两个线程彼此之间存在内存可见性问题
    // main线程读flag,分线程写flag

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag = " + isFlag());
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }


}