package LockTest;

public class test2 {

	public static void main(String [] args) {
		ConPro conPro= new ConPro();
		ConThread cThread = new ConThread(conPro);
		ProThread pThread = new ProThread(conPro);
		pThread.start();
		cThread.start();
	}
}
