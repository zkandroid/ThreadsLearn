package Threads.Learn;

import java.util.concurrent.ConcurrentHashMap;


public class ConcurrentHashMapT {
	private static  cTest c1= new cTest();
	public static void main(String [] args) throws InterruptedException {
		c1.h1.put("K1", 1);
		Thread.sleep(1000);
		for(int i=0;i<1000;i++) {
			new Thread(new Runnable() {
				
				public void run() {
					c1.add("K1");				
				}
			}).start();
			
		}
		//System.out.println("k1:"+c1.h1.get("K1"));
		
	}

}
class cTest extends Thread{
	public  ConcurrentHashMap<String, Integer> h1= new ConcurrentHashMap<String, Integer>();
	
	public void add(String key) {
		int value=h1.get(key);
		
		if(value==0) {
			h1.put(key, 1);
		}else {
			h1.put(key, value+1);
			System.out.println(h1.get("K1"));
		}
		
	}
	/**
	 * ，map本身是一个共享变量。当线程A执行map.get的时候，其它线程可能正在执行map.put，这样一来当线程A执行到map.put的时候，
	 * 线程A的值就已经是脏数据了，然后脏数据覆盖了真值，导致线程不安全
	 * 简单地说，ConcurrentHashMap的get方法获取到的是此时的真值，但它并不保证当你调用put方法的时候，当时获取到的值仍然是真值
	 * 所以，不要认为使用了线程安全类就是完全完全了，concurrenthashmap的线程安全是指他的put和get操作是原子操作，是线程安全的。
	 * 
	 */
}
