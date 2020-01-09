package a5_ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 线程池
 * 一、ExecuteService：真正的线程池接口，常见子类：ThreadPoolExecutor
 *  > void execute(Runnable command) : 执行任务/命令，一般执行Runnable
 *  > <T> Future<T> submit(Callable<T> task)：执行任务，泛型返回值，一般执行Callable
 *  > void shutdown()：关闭连接池
 *
 * 二、Executors：工具类、线程池的工厂类。用于创建并返回不同类型的线程池(ThreadPool)
 *  > Executors.newCachedThreadPool()：可创建新线程的
 *  > Executors.newFixedThreadPool(int num)：可重用的固定线程数的
 *  > Executors.newSingleThreadPool()：只有一个线程的
 *  > Executors.newScheduledThreadPool(int num)：有计划任务的(给定延迟后运行命令 || 定期执行)
 *
 * 三、线程池的使用：
 * 1.通过Executors工厂类，创建线程池对象
 * 2.ExecutorService对象获取线程装载 "线程体" 并启动线程
 *      > execute( Runnable )实现类的对象
 *      > submit ( Callable )实现类的对象
 * 3.关闭连接池
 *
 * 父类引用(ExecuteService) 强转子类对象 (ThreadPoolExecutor)
 */


public class ThreadPool {
    public static void main(String[] args) {
        // 1.通过Executors工厂类获得线程池对象
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 2.1 Executors.execute(Runnable run)
        service.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                    try {
                        System.out.println("Runnable 睡眠中");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 2.1 Executors.submit(Callable<T> call)
        service.submit(new Callable<>() {

            @Override
            public Object call() throws Exception {
                System.out.println("submit");
                return null;
            }
        });

        // 3.关闭线程池
        service.shutdown();
    }
}