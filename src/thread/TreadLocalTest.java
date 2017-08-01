package thread;

/**
 * Created by test on 17/4/20.
 */
import java.util.HashMap;

/**
 * 事实上，从本质来讲，就是每个线程都维护了一个map，而这个map的key就是threadLocal，而值就是我们set的那个值，
 * 每次线程在get的时候，都从自己的变量中取值，既然从自己的变量中取值，那肯定就不存在线程安全问题，总体来讲，
 * ThreadLocal这个变量的状态根本没有发生变化，他仅仅是充当一个key的角色，另外提供给每一个线程一个初始值。如果允许的话，我们自己就能实现一个这样的功能，只不过恰好JDK就已经帮我们做了这个事情。
 */
public class TreadLocalTest {

    static ThreadLocal<HashMap> map0 = new ThreadLocal<HashMap>(){
        @Override
        protected HashMap initialValue() {
            System.out.println(Thread.currentThread().getName()+"initialValue");
            return new HashMap();
        }
    };
    public void run(){
        Thread[] runs = new Thread[3];
        for(int i=0;i<runs.length;i++){
            runs[i]=new Thread(new T1(i));
        }
        for(int i=0;i<runs.length;i++){
            runs[i].start();
        }
    }
    public static class T1 implements Runnable{
        int id;
        public T1(int id0){
            id = id0;
        }
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
            HashMap map = map0.get();
            for(int i=0;i<10;i++){
                map.put(i, i+id*100);
                try{
                    Thread.sleep(100);
                }catch(Exception ex){
                }
            }
            System.out.println(Thread.currentThread().getName()+':'+map);
        }
    }
    /**
     * Main
     * @param args
     */
    public static void main(String[] args){


//        TreadLocalTest test = new TreadLocalTest();
//        test.run();
        int te = 0;
        te ++;
        te ++;
        te ++;
        System.out.println("te   "+te);
    }

    int te = 0;

    public void test(){
        te ++;
        System.out.println("te   "+te);
    }
}