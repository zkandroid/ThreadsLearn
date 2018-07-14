 import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
//如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，
//若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
//如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
//如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，
//直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
//从它们的具体实现来看，它们实际上也是调用了ThreadPoolExecutor，只不过参数都已配置好了。

//newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue；

//newSingleThreadExecutor将corePoolSize和maximumPoolSize都设置为1，也使用的LinkedBlockingQueue；

//newCachedThreadPool将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，使用的SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程
//https://www.cnblogs.com/dolphin0520/p/3932921.html
public class ThreadPool {
	
	
	public static void main(String [] args) {
		//ThreadPoolExecutor tPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));

		//ExecutorService tPoolExecutor = Executors.newFixedThreadPool(5);
		//ExecutorService tPoolExecutor=Executors.newSingleThreadExecutor();
		ExecutorService tPoolExecutor=Executors.newCachedThreadPool();
		for(int i=0;i<20;i++){
	        MyTask myTask = new MyTask(i);
	        tPoolExecutor.execute(myTask);
	       // System.out.println("线程池中线程数目："+tPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
	        //		tPoolExecutor.getQueue().size()+"，已执行玩别的任务数目："+tPoolExecutor.getCompletedTaskCount());
	    }
		tPoolExecutor.shutdown();
	}
	
}
class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
