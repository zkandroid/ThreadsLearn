package timeLearn;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class timeTest1 {
	public static void main(String [] args) throws InterruptedException {
		//isDaemon();
		//isnotDaemon();
		//beforPlan();
		//planAdd();
		planAddCance();
		
		
	}
	
	private static void isDaemon() throws InterruptedException {
		System.out.println("now time :"+new Date());
		Calendar calendar1= Calendar.getInstance();
		calendar1.add(Calendar.SECOND, 10);
		Date rDate = calendar1.getTime();
		myTask mTask = new myTask();
		Timer timer = new Timer(true);
		timer.schedule(mTask, rDate);
		System.out.println("WAIT 10 S");
		Thread.sleep(10000);
		
	}
	//计划时间晚于当前时间，在未来执行
	private static void isnotDaemon() throws InterruptedException {
		System.out.println("now time :"+new Date());
		Calendar calendar1= Calendar.getInstance();
		calendar1.add(Calendar.SECOND, 10);
		Date rDate = calendar1.getTime();
		System.out.println("计划时间 ："+rDate);
		myTask mTask = new myTask();
		Timer timer = new Timer();
		timer.schedule(mTask, rDate);
		System.out.println("WAIT 10 S");
		Thread.sleep(10000);
		
	}
	//计划时间早于当前时间，则立即执行
		private static void beforPlan() throws InterruptedException {
			System.out.println("now time :"+new Date());
			Calendar calendar1= Calendar.getInstance();
			calendar1.set(Calendar.SECOND, calendar1.get(Calendar.SECOND)-10);
			Date rDate = calendar1.getTime();
			System.out.println("计划时间 ："+rDate);
			myTask mTask = new myTask();
			Timer timer = new Timer();
			timer.schedule(mTask, rDate);
			//System.out.println("WAIT 10 S");
			//Thread.sleep(10000);
			
		}
		//指定日期之后按指定的日期周期执行某一任务
		private static void planAdd() throws InterruptedException {
			//System.out.println("now time :"+new Date());
			Calendar calendar1= Calendar.getInstance();
			//calendar1.set(Calendar.SECOND, calendar1.get(Calendar.SECOND)-10);
			Date rDate = calendar1.getTime();
			//System.out.println("计划时间 ："+rDate);
			myTaskadd mTask = new myTaskadd();
			Timer timer = new Timer();
			timer.schedule(mTask, new Date(),4000);//指定在new date()即现在之后按4s执行一次add
			//System.out.println("WAIT 10 S");
			//Thread.sleep(10000);
			
		}
		//指定日期之后按指定的日期周期执行某一任务，使用TimerTask类中的cancel()将planAddCance从任务队列中进行清除，
		//注意timer本身也有一个cance,timer.cance(),这个是将任务队列中的全部任务进行清空
		private static void planAddCance() throws InterruptedException {
					//System.out.println("now time :"+new Date());
					Calendar calendar1= Calendar.getInstance();
					//calendar1.set(Calendar.SECOND, calendar1.get(Calendar.SECOND)-10);
					Date rDate = calendar1.getTime();
					//System.out.println("计划时间 ："+rDate);
					myTaskadd mTask = new myTaskadd();
					Timer timer = new Timer();
					timer.schedule(mTask, new Date(),4000);//指定在new date()即现在之后按4s执行一次add
					//System.out.println("WAIT 10 S");
					//Thread.sleep(10000);
					myTaskCance mCance = new myTaskCance();
					timer.schedule(mCance, new Date(),5000);
					
				}

}

class myTask extends TimerTask{

	@Override
	public void run() {
		System.out.println("任务执行了，时间 为："+new Date());
		
	}
	
}

class myTaskadd extends TimerTask{
	private int index=0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		index++;
		System.out.println("THE index :"+index);
	}
	
}
//TimerTask类中的cancel()将planAddCance从任务队列中进行清除
class myTaskCance extends TimerTask{
	private int canceIndex=0;

	@Override
	public void run() {
		canceIndex++;
		if(canceIndex==5) {
			System.out.println("cance");
			this.cancel();
		}
		System.out.println("canceindex:"+canceIndex);
	}
	
}