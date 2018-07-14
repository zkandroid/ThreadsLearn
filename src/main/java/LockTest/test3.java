package LockTest;

public class test3 {
	
	public static void main(String [] args) {
		ThreadLock cPro = new ThreadLock();
		LockPro lPro = new LockPro(cPro);
		LockCon lCon = new LockCon(cPro);
		lPro.start();
		lCon.start();
		//死锁了
	}

}
