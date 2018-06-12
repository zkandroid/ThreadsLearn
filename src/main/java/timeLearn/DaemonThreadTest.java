package timeLearn;
/**
 * 两种线程，守护线程和用户线程
 * 当进程中不再有非守护线程时他也就停止了。典型应用：垃圾回收线程，当进程中没有非守护线程了，垃圾回收线程已就停止了
 * @author zk
 *
 */
public class DaemonThreadTest {
	public static void main(String [] args) throws InterruptedException {
		DeamonThread dTest = new DeamonThread();
		dTest.setDaemon(true);
		dTest.start();
		Thread.sleep(5000);
		System.out.println("我离开了，守护线程也没有东西可以守护了，使用守护线程也不打印了");
	}

}

class DeamonThread extends Thread{
	private int i =0;
	
	public void run() {
		try {
			while(true) {
				i++;
				System.out.println("thread i:"+i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
