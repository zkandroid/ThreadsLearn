package Threads.Learn;


//非线程安全
public class HasSelfPrivateNumTest{

	public static void main(String[] args) {
		HasSelfPrivateNum hPrivateNum = new HasSelfPrivateNum();
		myThreadHa nHa =new myThreadHa(hPrivateNum);
		myThreadHb nHb = new myThreadHb(hPrivateNum);
		nHa.start();
		nHb.start();
		
	}
	
}

class HasSelfPrivateNum {
	private int num =0;
	
	synchronized public void add(String useName) {
		try {
			if ("a".equals(useName)) {
				num =100;
				System.out.println("a set over");
				Thread.sleep(2000);
			} else {
				num =200;
				System.out.println("b set over");
			}
			System.out.println(useName+" NUM="+num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class myThreadHa extends Thread{
	private HasSelfPrivateNum hNum;
	public myThreadHa(HasSelfPrivateNum hNum) {
		this.hNum =hNum;
	}
	public  void run() {
		hNum.add("a");
		
	}
}

class myThreadHb extends Thread{
	private HasSelfPrivateNum hNum;
	public myThreadHb(HasSelfPrivateNum hNum) {
		this.hNum =hNum;
	}
	public  void run() {
		hNum.add("b");
		
	}
}
