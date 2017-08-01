package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by test on 17/4/20.
 */
public class AtomicTest {

    private AtomicInteger count = new AtomicInteger();

    /**
     *     对于原子操作类，最大的特点是在多线程并发操作同一个资源的情况下，使用Lock-Free算法来替代锁，
     *     这样开销小、速度快，对于原子操作类是采用原子操作指令实现的，从而可以保证操作的原子性。
     *     什么是原子性？比如一个操作i++；实际上这是三个原子操作，先把i的值读取、然后修改(+1)、最后写入给i。
     *     所以使用Atomic原子类操作数，比如：i++；那么它会在这步操作都完成情况下才允许其它线程再对它进行操作，而这个实现则是通过Lock-Free+原子操作指令来
     *     而关于Lock-Free算法，则是一种新的策略替代锁来保证资源在并发时的完整性的，Lock-Free的实现有三步：
     *     1、循环（for(;;)、while）
     *     2、CAS（CompareAndSet）
     *     3、回退（return、break）
     *     用法
     *     比如在多个线程操作一个count变量的情况下，则可以把count定义为AtomicInteger，
     *
     *     在每个线程中通过increment()来对count进行计数增加的操作，或者其它一些操作。这样每个线程访问到的将是安全、完整的count。
     */
    public int getCount(){
        return count.get();
    }

    public void increment(){
        count.incrementAndGet();
    }
}
