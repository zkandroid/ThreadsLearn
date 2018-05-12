package Threads.Learn;

/**
 * 并发编程的三个概念：原子性，可见性，有序性
 * volatile关键字能保证变量的可见性但是不能保证他的原子性，在一定程度上能保证他的有序性
 * 当对变量的写操作不依赖于当前值并且该变量没有包含在具有其它变量的不变式中时可以用volatile关键字来保证并发编程的安全
 * 具体参考这篇博文，感谢作者
 * {@link https://www.cnblogs.com/dolphin0520/p/3920373.html}
 * @author zk
 * 原子性：即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
 * 可见性：指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
 * 有序性：即程序执行的顺序按照代码的先后顺序执行
 *
 */
public class VolatileTest {
    public volatile int inc = 0;
    //public  int  inc =0;
     
    public void increase() {
        inc++;
    }
     
    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                	//synchronized (test) {
                		  for(int j=0;j<1000;j++)
                      		test.increase();
					//}                 						                        
                };
            }.start();
        }
         
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);//因为volatile关键字虽然能保证inc的可见性，但是并不能保证它的原子性，所以结果不会是10000
        //比如当一个线程读到了inc=10，它此时只是对他进行了读操作，但是它线程阻塞了，这时候另一个线程被唤醒，此时因为前一个线程只是读，所以它读到的还是10，这时候它再对inc+1，则inc=11,而此时前一个
        //线程被唤醒，因为它已经过了读这一步，所以它刚刚读到10+1也是=11，所以这是这两个线程完成后的结果仅仅是11
    }
}
