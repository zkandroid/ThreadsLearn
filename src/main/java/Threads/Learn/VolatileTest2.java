package Threads.Learn;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 一直思考在多线程之中对一个对象的写需要加锁，但是对一个对象的读因为没有对他有写操作，所以不会改变他的值，这样对其它线程来说应该时安全的，所以就不需要进行加锁了
 * 但是，too yong too  simple,
 * 因为你操作的变量都在缓存(cache)中，CPU0/1可能会同时保存一份变量缓存，也就是说你的变量在缓存里是两份，只有一个CPU（核）更新的话是不一定触发另外一个CPU立即更新的
 * 所以啊：
 * 如果只是读操作，没有写操作，则可以不用加锁，此种情形下，建议变量加上final关键字；
 * 如果有写操作，但是变量的写操作跟当前的值无关联，且与其他的变量也无关联，则可考虑变量加上volatile关键字，同时写操作方法通过synchronized加锁
 * 如果有写操作，且写操作依赖变量的当前值(如：i++)，则getXXX和写操作方法都要通过synchronized加锁。
 * 如果只有一个线程写，多个线程读的话，可以使用volatile关键字
 * 
 * 
 * 下面第二个例子有点问题，
 * 
 * @author zk
 *
 */
public class VolatileTest2 {
	static  Map<Integer, String> xMap = new HashMap<Integer, String>();//1,因为但是变量的写操作跟当前的值无关联，且与其他的变量也无关联，则可考虑变量加上volatile关键字，同时写操作方法通过synchronized加锁
	static int ins;
	volatile static int inc;//2,如果只有一个线程写，多个线程读的话，可以使用volatile关键字
	static int ina;//如果有写操作，且写操作依赖变量的当前值(如：i++)，则getXXX和写操作方法都要通过synchronized加锁。
	public static void main(String [] args) throws InterruptedException {
		/*//1,因为但是变量的写操作跟当前的值无关联，且与其他的变量也无关联，则可考虑变量加上volatile关键字，同时写操作方法通过synchronized加锁
		for(int x=0;x<100;x++) {
			new Thread() {
				public void run() {
					ins++;
					add(ins);
				}
			}.start();System.out.println(ina);
		}
		
		for(String value: xMap.values()) {
			System.out.println(value);
		}*/
		///*//2，如果只有一个线程写，多个线程读的话，可以使用volatile关键字
		for(int x=0;x<100000;x++){
			inc++;
		}
		for(int x=0;x<10;x++) {
				new Thread() {
					public void run() {
						System.out.println(inc);
					}	
				}.start();			
		}
		
		//*/
		
		/*//3，如果有写操作，且写操作依赖变量的当前值(如：i++)，则getXXX和写操作方法都要通过synchronized加锁
		for(int x=0;x<100;x++) {
			new Thread() {
				public void run() {
				synchronized(this) {	
					for (int i = 0; i <100; i++) {
						ina++;
					}
					
				}
				}	
			}.start();
		
		}
		Thread.sleep(2000);
		
		
		*/
		
	
			
	}
	
	public static void add(int num){
		synchronized (xMap) {
			xMap.put(num, "value:"+num);
		}
	}
}
