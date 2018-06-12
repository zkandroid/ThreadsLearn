package LockTest;

public class Test {
	public static void main(String [] args) {
		ProducerConsumer pc = new ProducerConsumer();
		producerThread a = new producerThread(pc);
		a.start();
		consumerThread b = new consumerThread(pc);
		
		b.start();
		
	}

}
