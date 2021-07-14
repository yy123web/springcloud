package zzj;

public class RunnableDemo01 {
    public static void main(String[] args) {
        MyThread mt1=new MyThread("线程A");
        MyThread mt2=new MyThread("线程B");
        Thread t1 = new Thread(mt1);
        Thread t2 = new Thread(mt2);
        t1.start();
        t2.start();
    }
}
