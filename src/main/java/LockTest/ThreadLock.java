package LockTest;

public class ThreadLock {
	private final Object le = new Object();
	private final Object ri= new Object();
	
	
	public void pro() {
		try {
			synchronized (le) {
				Thread.sleep(2000);
				synchronized (ri) {
					System.out.println("ri");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void con() {
		try {
			synchronized (ri) {
				Thread.sleep(2000);
				synchronized (le) {
					System.out.println("le");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
