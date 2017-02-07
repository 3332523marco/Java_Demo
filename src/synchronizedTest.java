/**
 * Created by marco on 16/10/11.
 */
public class synchronizedTest {
    //非同步
    static void method(Thread thread){
        System.out.println("begin "+thread.getName());
        try{
            Thread.sleep(2000);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("end "+thread.getName());
    }
//    begin Thread-0
//    begin Thread-1
//    begin Thread-2
//    end Thread-1
//    end Thread-2
//    end Thread-0

    //同步方式一：同步方法
    synchronized static void method1(Thread thread){//这个方法是同步的方法，每次只有一
        System.out.println("begin "+thread.getName());
        try{
            Thread.sleep(2000);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("end "+thread.getName());
    }
//    begin Thread-0
//    end Thread-0
//    begin Thread-2
//    end Thread-2
//    begin Thread-1
//    end Thread-1

    //同步方式二：同步代码块
    static void method2(Thread thread){
        synchronized(synchronizedTest.class) {
            System.out.println("begin "+thread.getName());
            try{
                Thread.sleep(2000);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println("end "+thread.getName());
        }
    }
//    begin Thread-0
//    end Thread-0
//    begin Thread-2
//    end Thread-2
//    begin Thread-1
//    end Thread-1

    //同步方式三：使用同步对象锁
    private static Object _lock1=new Object();
    private static byte _lock2[]={};//据说，此锁更可提高性能。源于：锁的对象越小越好
    static void method3(Thread thread){
        synchronized(_lock1) {
            System.out.println("begin "+thread.getName());
            try{
                Thread.sleep(2000);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println("end "+thread.getName());
        }
    }

//    begin Thread-0
//    end Thread-0
//    begin Thread-2
//    end Thread-2
//    begin Thread-1
//    end Thread-1
public static void main(String[] args){
    //启动3个线程，这里用了匿名类
    for(int i=0;i<3;i++){
        new Thread(){
            public void run(){
//                    method(this);
//method1(this);
//method2(this);
                method3(this);
            }
        }.start();
    }

}
}
