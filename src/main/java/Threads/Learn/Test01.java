package Threads.Learn;

import java.util.Hashtable;

/**
 * 描述:对synchronized的理解
 * {@link http://www.cnblogs.com/GnagWang/archive/2011/02/27/1966606.html}
 * @author zk
 * @date 2018-425
 *
 */
public class Test01{
	private Hashtable<String,Integer> h = new Hashtable<String,Integer>();
	public static void main(String[] args) throws Exception {
		
		long l1 = System.currentTimeMillis();
		///*
		Test01 test01 = new Test01();
		Test01 test02 = new Test01();
		test01.stateThread();
		test02.stateThread();
		//*/
		long l2 = System.currentTimeMillis();
		System.out.println((l2-l1)/1000);
	}
	//因为对h加锁,所有怎么调用都不会发生同时操作h的情况,h是安全的,但是线程是不安全的
	public void stateThread() {
		for(int i=0;i<1000;i++) {
			new Thread(new Runnable(){
				public void run() {
					synchronized (h) {//对h加锁,保证h安全
					h.put("test",1);
					Integer i1 = h.get("test");
					h.put("test",2);
					Integer i2 = h.get("test");
					if(i1 == i2) {
						System.out.println(i1 + ":" + i2);
					}/*synchronized (this) {//对这个代码块加锁,
					 for (int i = 0; i < 5; i++) {  
		                    System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);  
		               }  
					}*/
				}
			}
			}).start();
		}
		
	}
		
}
