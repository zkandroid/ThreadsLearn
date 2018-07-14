package Threads.Learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * hashMap的效率最高,但是是非线程安全的,在put时可能会发生hash值一样，然后又被放左了同一个位置,在扩容时可能会发生死循环,因为在多线程会产生冲突,所以在多线程情况下它的效率反而还没有ConcurrentHashMap
 * Hashtable synchronizedMap ConcurrentHashMap是线程安全的,其中ConcurrentHashMap效率最高,因为他只锁住了一部分
 * 但是,如果写线程大于读线程,最好别用
 * {@link https://yemengying.com/2016/05/07/threadsafe-hashmap/}
 * 感谢Giraffe
 * @author zk
 *
 */
public class synchronizedClass {
	
	public static void main(String [] args) throws InterruptedException {
		Map<String, Integer> hMap = new HashMap<String, Integer>();
		/*个人觉得 HashMap 在并发时可能出现的问题主要是两方面,首先如果多个线程同时使用put方法添加元素，
		*而且假设正好存在两个 put 的 key 发生了碰撞(根据 hash 值计算的 bucket 一样)，那么根据 HashMap 的实现，
		*这两个 key 会添加到数组的同一个位置，这样最终就会发生其中一个线程的 put 的数据被覆盖
		*。第二就是如果多个线程同时检测到元素个数超过数组大小* loadFactor ，
		*这样就会发生多个线程同时对 Node 数组进行扩容，都在重新计算元素位置以及复制数据，
		*但是最终只有一个线程扩容后的数组会赋给 table，也就是说其他线程的都会丢失，并且各自线程 put 的数据也丢失
		*而且在他扩容的时候有可能会造成死循环
		*HashMap是采用数组和链表/红黑树(当长度大于8是会指定变成红黑树(因为红黑树的最坏查找长度logn
		**/
		crunchifyPerformTest(hMap);//多线程情况下反而没有ConcurrentHashMap效率高
		hashMapTest(hMap);//单线程下及时put数目是ConcurrentHashMap的五倍,速度也要比它快一倍
		Map<String, Integer>  htMap = new Hashtable<String, Integer>();//线程安全,内部使用了synchronized关键字
		crunchifyPerformTest(htMap);
		Map<String, Integer > syzMap = Collections.synchronizedMap(new HashMap<String, Integer>());//线程安全,也是采用synchronized
		crunchifyPerformTest(syzMap);
		Map<String, Integer > cMap = new ConcurrentHashMap<String,Integer>();
		crunchifyPerformTest(cMap);
		/**
		 * CHM不但是线程安全的，而且比HashTable和synchronizedMap的性能要好。
		 * 相对于HashTable和synchronizedMap锁住了整个Map，CHM只锁住部分Map。
		 * CHM允许并发的读操作，同时通过同步锁在写操作时保持数据完整性
		 * 
		 * CHM允许并发的读和线程安全的更新操作
		 * 在执行写操作时，CHM只锁住部分的Map
		 * 并发的更新是通过内部根据并发级别将Map分割成小部分实现的
		 * 高的并发级别会造成时间和空间的浪费，低的并发级别在写线程多时会引起线程间的竞争
		 * CHM的所有操作都是线程安全
		 * CHM返回的迭代器是弱一致性，fail-safe并且不会抛出ConcurrentModificationException异常
		 * CHM不允许null的键值可以使用CHM代替HashTable，但要记住CHM不会锁住整个Map
		 */
		
		Vector<Integer> vector = new Vector<Integer>();//线程安全,采用synchronized关键字,和ArrayList想对应
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();//栈的特点先进先出,线程安全,采用synchronized关键字
		//还有enum枚举和StringBuffer是线程安全，而StringBuilder是线程不安全的
		/**
		 * 不是线程安全根本原因就是不具有原子性,可见性,顺序性中的一条特性而又没有给它加锁
		 */
	}
	
	private static void hashMapTest(Map<String, Integer> hMap) {
		System.out.println("Test started for: " + hMap.getClass());
		 long startTime = System.nanoTime();
		for (int i = 0; i < 2500000; i++) {
            Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);
            // Retrieve value. We are not using it anywhere
            Integer crunchifyValue = hMap.get(String.valueOf(crunchifyRandomNumber));
            // Put value
            hMap.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
        }
		long entTime = System.nanoTime();
		 long totalTime = (entTime - startTime) / 1000000L;
		 System.out.println("For " + hMap.getClass() + " the average time is " + totalTime / 5 + " ms\n");
		 
	}

	public static void crunchifyPerformTest(final Map<String, Integer> crunchifyThreads) throws InterruptedException {
        System.out.println("Test started for: " + crunchifyThreads.getClass());
        long averageTime = 0;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            ExecutorService crunchifyExServer = Executors.newFixedThreadPool(5);
            for (int j = 0; j < 5; j++) {
                crunchifyExServer.execute(new Runnable() {
                    @SuppressWarnings("unused")
                    public void run() {
                        for (int i = 0; i < 500000; i++) {
                            Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);
                            // Retrieve value. We are not using it anywhere
                            Integer crunchifyValue = crunchifyThreads.get(String.valueOf(crunchifyRandomNumber));
                            // Put value
                            crunchifyThreads.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
                        }
                    }
                });
            }
            // Make sure executor stops
            crunchifyExServer.shutdown();
            // Blocks until all tasks have completed execution after a shutdown request
            crunchifyExServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
        }
        System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
    }

}
