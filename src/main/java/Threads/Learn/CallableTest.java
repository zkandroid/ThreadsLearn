package Threads.Learn;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 实现callab接口会获得线程的返回值，实现它的call方法
 * @author zk
 *
 */

class TestCallable implements Callable<Integer>{
	private int number;
	
	public TestCallable(int number) {
		this.number = number;
	}
	//斐波那契数列
	public int fibonacci(int n) {
    	if(n == 0 || n ==1) {
    		return 1;
    	}
		return n+fibonacci(n-1)+fibonacci(n-2);
	}

	public Integer call() throws Exception {
		
		return fibonacci(number);
	}
	
}

public class CallableTest {
	public static void main(String [] args) {
		ExecutorService eService = Executors.newCachedThreadPool();
		
		ArrayList<Future<Integer>> results =new ArrayList<Future<Integer>>();
		for(int i=0;i<10;i++) {
			results.add(eService.submit(new TestCallable(i)));
		}
		for (Future<Integer> future : results) {
			//判断线程是否完成
			if (future.isDone()) {
				try {
					System.out.println(future+":"+future.get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					eService.shutdown();
				}
			}else {
				System.out.println(future + ":NoDONe");
			}
		}
	}
	

}
