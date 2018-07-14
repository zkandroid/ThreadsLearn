package LockTest;

public class ProThread extends Thread{
	private ConPro cPro;
	public ProThread(ConPro cPro) {
		this.cPro=cPro;
	}
	public void run() {
		cPro.pro();
	}

}
