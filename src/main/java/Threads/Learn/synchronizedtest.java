package Threads.Learn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class synchronizedtest {

	private  static Map<String, Integer> maptest = new HashMap<String, Integer>();
	
	public static void main(String [] args) {
		synchronizedtest stest1 = new synchronizedtest();
		synchronizedtest stest2 = new synchronizedtest();
		for(int x = 0;x<100;x++) {
			maptest.put(""+x, x);
		}
		
		
		stest1.startTheard();
		//stest2.startTheard();
		
		
	}
	public void startTheard() {
		for(int i=0;i<2;i++) {
			new Thread(new Runnable(){
				@SuppressWarnings("unlikely-arg-type")
				public void run() {
					synchronized (maptest) {//对h加锁,保证h安全
						Iterator<Map.Entry<String, Integer>> it = maptest.entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry<String, Integer> entry = it.next();
							//System.out.println("entry:"+entry);
							if(entry.getValue()%3==0) {
								System.out.println("entry:"+entry);
								it.remove();
							}
						}/*synchronized (this) {//对这个代码块加锁,
					 for (int i = 0; i < 5; i++) {  
		                    System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);  
		               }  
					}*/
				}
			}
			}).start();
		}
		System.out.println("--------");
		
	}
		
}

