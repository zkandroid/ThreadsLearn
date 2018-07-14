package LockTest;

public class LockPro extends Thread {
	private ThreadLock lPro;
	public LockPro(ThreadLock lPro) {
		this.lPro=lPro;
	}
	public void run() {
		lPro.pro();
	}

}
