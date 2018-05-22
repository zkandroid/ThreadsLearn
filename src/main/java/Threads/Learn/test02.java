package Threads.Learn;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 描述：这个类主要是记录今天对ArrayList操作报java.util.ConcurrentModificationException异常
 * @author zk
 * @date 2018-4-26
 * @version 0.0.1
 * {@link http://zhoujianghai.iteye.com/blog/1041555}
 *
 */
public class test02 {
	private ArrayList<String> aList = new ArrayList<String>();
	
	public static void main(String [] args) {
		test02 test02 = new test02();
		test02.add();
		test02.times();
		
		
	}

	public void times() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				if(aList.size() !=0) {
					synchronized(this) {
					try {
						Random random = new Random();
						int i2 = random.nextInt(100);
						ntfClientTimeOver(Integer.toString(i2));
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("ntfClientGameOver have a Exception:"+e);
					}
				}
			}
			}	
		};
		timer.schedule(task,0, 1000);
	}
	
	protected void ntfClientTimeOver(String S) {
		for(int x =0;x<aList.size();x++) {
			if(S.equals(aList.get(x))) {
				remove(S);
				System.out.println("ntfClientTimeOver:"+S);
			}
			}
	}
	/**
	 * 如果这样写会报java.util.ConcurrentModificationException异常，这是因为
	 * ArrayList的remove方法只是修改了modCount的值，并没有修改expectedModCount，
	 *导致modCount和expectedModCount的值的不一致性，当next()时则抛出ConcurrentModificationException异常
	 *因此使用Iterator遍历集合时，不要改动被迭代的对象，可以使用 Iterator 本身的方法 remove() 来删除对象，
	 *Iterator.remove() 方法会在删除当前迭代对象的同时维护modCount和expectedModCount值的一致性。
	 * @param string
	 */
	
	public void ntfClientTimeOver2(String string) {
		for(String string2:aList) {
			if(string.equals(string2)) {
				remove(string);
				System.out.println("ntfClientTimeOver:"+string);
			}
		}
	}

	public void add() {
		synchronized(aList) {
			for(int x =0;x<100;x++) {
				aList.add(Integer.toString(x));
			}
		}
	}
	public void remove(String s) {
		synchronized (aList) {
			aList.remove(s);
		}
	}

}
