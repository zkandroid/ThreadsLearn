package ThreadGroupLearn;
//线程组的作用主要是可以批量的管理线程
public class ThreadGroupTest {
	public static void main(String [] args) throws InterruptedException {
		ThreadA a= new ThreadA();
		ThreadB b=new ThreadB();
		ThreadGroup group = new ThreadGroup("MY thread Group");
		ThreadGroup ogroup = new ThreadGroup("other thread Group");
		
		Thread aThread = new Thread(group,a);
		Thread bThread = new Thread(group, b);
		Thread cThread = new Thread(ogroup,a);
		//Thread dThread = new Thread(ogroup, b);
		aThread.start();
		bThread.start();
		cThread.start();
		System.out.println("group线程组活跃的线程数："+group.activeCount());
		System.out.println("group线程组线程组名称："+group.getName());
		System.out.println("ogroup线程组活跃的线程数："+ogroup.activeCount());
		System.out.println("ogroup线程组线程组名称："+ogroup.getName());
		
		Thread.sleep(5000);
		group.interrupt();
		//ogroup.interrupt();
		System.out.println("group线程组 interrupt!");
		
	}

}

class ThreadA extends Thread{
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			System.out.println("Thread name:"+Thread.currentThread().getName());
			/*try {
				//Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}
class ThreadB extends Thread{
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			System.out.println("Thread name= "+Thread.currentThread().getName());
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}