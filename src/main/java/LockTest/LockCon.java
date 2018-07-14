package LockTest;

public class LockCon extends Thread {
	private ThreadLock tLock;
	public LockCon(ThreadLock tLock) {
		this.tLock =tLock;
	}

	public void run() {
		tLock.con();
	}
}
