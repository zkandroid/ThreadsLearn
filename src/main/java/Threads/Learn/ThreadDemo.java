package Threads.Learn;

import java.util.HashMap;
import java.util.Map;

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
    private  static Map<Integer, String> maptest = new HashMap<Integer, String>();
    public void run(){ 
    	synchronized (this) {//不加同步锁的话,可能会发生线程不安全,因为下面开了三个线程共同执行这个代码块
    		for (int i=0;i<100;i++)  
            {  
    			maptest.put(i, ":"+i);
                if(ticket > 0){  
                    System.out.println(Thread.currentThread().getName()+" Runnable ticket = " + ticket--); 
                    //System.out.println(Thread.currentThread().getName()+ "fibonacci(10):."+fibonacci(10));
                    Thread.yield();//调用的是对线程调度器的一种建议，它在声明：我已经执行完生命周期中的最重要的部分了，此时是切换给其它任务执行一段时间的大好时机
                }  
            }  
		}
        
    }
    public int fibonacci(int n) {
    	if(n == 0 || n ==1) {
    		return 1;
    	}
		return n+fibonacci(n-1)+fibonacci(n-2);
	}
}  
  
public class ThreadDemo{  
		
    public static void main(String[] args){
    	/*
        new MyThread().start();  
        new MyThread().start();  
        new MyThread().start();  
        System.out.println("-----------------");*/
        MyRunnable my = new MyRunnable();  
        new Thread(my).start();  
        new Thread(my).start();  
       // new Thread(my).start();
    }  
    
    
}  