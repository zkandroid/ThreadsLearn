package LockTest;

public class consumerThread extends Thread{

private ProducerConsumer pc ;
	
	public consumerThread(ProducerConsumer pc) {
		this.pc =pc;
	}
	public void run() {
		for(int x =0;x<10;x++)
		pc.consumer();
		
	}
}
