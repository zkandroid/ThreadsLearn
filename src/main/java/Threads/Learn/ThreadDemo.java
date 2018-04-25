package Threads.Learn;

/**
 * Java中实现多线程有两种方法：继承Thread类、实现Runnable接口，在程序开发中只要是多线程，肯定永远以实现Runnable接口为主，
 * 因为实现Runnable接口相比继承Thread类有如下优势：1、可以避免由于Java的单继承特性而带来的局限；
 *  2、增强程序的健壮性，代码能够被多个线程共享，代码与数据是独立的；
 * 3、适合多个相同程序代码的线程区处理同一资源的情况。
 * @author zk
 * {@link https://blog.csdn.net/ns_code/article/details/17161237}
 *
 */
class MyThread extends Thread{  
    private int ticket = 5;  
    public void run(){  
        for (int i=0;i<10;i++)  
        {  
            if(ticket > 0){  
                System.out.println(Thread.currentThread().getName()+" ticket = " + ticket--);  
            }  
        }  
    }  
}  

class MyRunnable implements Runnable{  
    private int ticket = 5;  
    public void run(){ 
    	synchronized (this) {//不加同步锁的话,可能会发生线程不安全,因为下面开了三个线程共同执行这个代码块
    		for (int i=0;i<10;i++)  
            {  
                if(ticket > 0){  
                    System.out.println(Thread.currentThread().getName()+" Runnable ticket = " + ticket--);  
                }  
            }  
		}
        
    }  
}  
  
public class ThreadDemo{  
    public static void main(String[] args){  
        new MyThread().start();  
        new MyThread().start();  
        new MyThread().start();  
        System.out.println("-----------------");
        MyRunnable my = new MyRunnable();  
        new Thread(my).start();  
        new Thread(my).start();  
        new Thread(my).start();
    }  
}  