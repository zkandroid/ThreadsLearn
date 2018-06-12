package LockTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * lock对象是synchronized关键字的进阶用法，synchronized关键字有的功能他都有，而且他还有synchronized不具有的功能
 * 这里用到了condition对象，他的await和signal，signalall具有wait 和notify（）notifyAll()的功能，但是object的notify只是随机的通知线程，
 * 而condition可以通知指定的线程，
 * 比如condition1 = lock.newCondition();则condition1.notifyAll就可以只通知condition1.awit所wait的线程，这极大的提高了效率。
 * @author zk
 *
 */
public class ProducerConsumer {
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue =false;
	
	public void producer(){
		try {
			lock.lock();
			while(hasValue ==true) { 
				condition.await();
			}
			System.out.println("producer produce a value");
			hasValue = true;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void consumer() {
		try {
			lock.lock();
			while(hasValue == false) {
				condition.await();
			}
			System.out.println("consumer consum a value");
			hasValue = false;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

}
