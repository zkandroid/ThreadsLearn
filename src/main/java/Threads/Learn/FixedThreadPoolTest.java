package Threads.Learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Excutor 可以管理线程，简化并发编程
 * @author zk
 *
 */
public class FixedThreadPoolTest {
	public static void main(String [] args) {
		///*
		ExecutorService eService = Executors.newFixedThreadPool(5);//限定线程数量5
		for(int i =0;i<6;i++) {//因为限定的线程数量是5，所以最多只有5个线程
			eService.execute(new MyRunnable());
		}
		eService.shutdown();
		//*/
		ExecutorService eService2 = Executors.newCachedThreadPool();
		for(int i =0;i<6;i++) {
			eService2.execute(new MyRunnable());
		}
		eService2.shutdown();
		
	}
	

}
