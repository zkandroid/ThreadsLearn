
package Threads.Learn;

import java.util.HashMap;
import java.util.Map;

public class ZhangReadTest{
	
	
	public static void main(String [] args) {
		ZhangRead zhangRead = new ZhangRead();
		
		Theadput pTheadput = new Theadput(1, "TEST1", zhangRead);
		Theadget theadget = new Theadget(1, zhangRead);
		//Theadremove theadremove = new Theadremove(1, zhangRead);
		pTheadput.start();
		theadget.start();
		//theadremove.start();
	}
	
	
}

class ZhangRead {
	private Map<Integer, String> map =new HashMap<Integer, String>();
	
	public void putMap(int x ,String S) {
		synchronized(map) {
		for(int m =0;m<10;m++) {
		map.put(m, Integer.toString(m));
		System.out.println("put:"+m);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
	}
	
	public void removeMap(int X) {
		if(map.containsValue(X))
			map.remove(X);
		System.out.println("remove:"+X);
	}
	
	public void getMap(int x) {
		synchronized(map) {
		for(int y=0;y<10;y++) {
		System.out.println("GET:"+map.get(y));
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
	}
}
class Theadput extends Thread{
	private ZhangRead zRead;
	private int index;
	private String value;
	public Theadput (int index,String value,ZhangRead zRead) {
		this.index =index;
		this.value = value;
		this.zRead =zRead;
	}
	public void run() {
		zRead.putMap(index, value);
		
	}
}
class Theadget extends Thread{
	private ZhangRead zRead;
	private int index;
	
	public Theadget (int index,ZhangRead zRead) {
		this.index =index;
		this.zRead =zRead;
	}
	public void run() {
		zRead.getMap(index);
		
	}
}
class Theadremove extends Thread{
	private ZhangRead zRead;
	private int index;
	
	public Theadremove (int index,ZhangRead zRead) {
		this.index =index;
		
		this.zRead =zRead;
	}
	public void run() {
		zRead.removeMap(index);
		
	}
}
