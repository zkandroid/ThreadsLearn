package LockTest;

public class Test {
	public static void main(String [] args) {
		ProducerConsumer pc = new ProducerConsumer();
		producerThread[] pro= new producerThread[10];
		consumerThread[] con = new consumerThread[10];
		for(int i=0;i<10;i++) {
			pro[i]=new producerThread(pc);
			con[i]= new consumerThread(pc);
			pro[i].start();
			con[i].start();//程序运行后会出现假死，因为condition使用singnal唤醒的可能是同类，改成singnalAll就好了
		}
	}

}
