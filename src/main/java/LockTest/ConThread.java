package LockTest;

public class ConThread extends Thread{

	private ConPro cPro;
	public ConThread(ConPro cPro) {
		this.cPro=cPro;
	}
	
	public void run() {
		cPro.con();
	}
}
