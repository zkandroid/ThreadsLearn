package LockTest;



public class producerThread extends Thread{
	private ProducerConsumer pc ;
	
	public producerThread(ProducerConsumer pc) {
		this.pc =pc;
	}
	public void run() {
		for(int x =0;x<10;x++)
		pc.producer();
		
	}

}
