package LockTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConPro {

	private int [] nums= {1,2,3,4,5,6,7,8,9,10};
	private char[] chars= {'a','b','c','d','e'};
	private ReentrantLock lock= new ReentrantLock();
	private Condition condition= lock.newCondition();
	private int count=0;
	
	public void pro() {
		try {
			lock.lock();
			for(int n:nums) {
				while(count==2) {
					condition.await();
				}
				count++;
				System.out.print(n);
				condition.signal();
			}
			
		}catch (InterruptedException e) {
			// TODO: handle exception
		} 
		finally {
			// TODO: handle finally clause
			lock.unlock();
		}
		
	}
	
	public void con() {
		try {
			lock.lock();
			for(char n:chars) {
				while(count==0) {
					condition.await();
				}
				
				System.out.print(n);
				count=0;
				condition.signal();
			}
			
		}catch (InterruptedException e) {
			// TODO: handle exception
		} 
		finally {
			// TODO: handle finally clause
			lock.unlock();
		}
		
	}
}
