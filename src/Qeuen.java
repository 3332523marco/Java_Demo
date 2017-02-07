import java.util.PriorityQueue;

/**
 * Created by marco on 16/7/18.
 */
public class Qeuen {
//非阻塞队列
// 非阻塞队列的时候有一个很大问题就是：它不会对当前线程产生阻塞，那么在面对类似消费者-生产者的模型时，就必须额外地实现同步策略以及线程间唤醒策略，这个实现起来就非常麻烦。
//非阻塞队列中的几个主要方法：
//    add(E e):将元素e插入到队列末尾，如果插入成功，则返回true；如果插入失败（即队列已满），则会抛出异常；
//    remove()：移除队首元素，若移除成功，则返回true；如果移除失败（队列为空），则会抛出异常；
//    offer(E e)：将元素e插入到队列末尾，如果插入成功，则返回true；如果插入失败（即队列已满），则返回false；
//    poll()：移除并获取队首元素，若成功，则返回队首元素；否则返回null；
//    peek()：获取队首元素，若成功，则返回队首元素；否则返回null
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    public static void main(String[] args)  {
        Qeuen test = new Qeuen();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while(true){
                synchronized (queue) {
                    while(queue.size() == 0){
                        try {
                            System.out.println("队列空，等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll();          //每次移走队首元素
                    queue.notify();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                }
            }
        }
    }

    class Producer extends Thread{

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while(true){
                synchronized (queue) {
                    while(queue.size() == queueSize){
                        try {
                            System.out.println("队列满，等待有空余空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);        //每次插入一个元素
                    queue.notify();
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                }
            }
        }
    }

    //阻塞队列   阻塞队列使用最经典的场景就是socket客户端数据的读取和解析，读取数据的线程不断将数据放入队列，然后解析线程不断从队列取数据解析。
//    　　put方法用来向队尾存入元素，如果队列满，则等待；
//       take方法用来从队首取元素，如果队列为空，则等待；
//       offer方法用来向队尾存入元素，如果队列满，则等待一定的时间，当时间期限达到时，如果还没有插入成功，则返回false；否则返回true；
//       poll方法用来从队首取元素，如果队列空，则等待一定的时间，当时间期限达到时，如果取到，则返回null；否则返回取得的元素；
//    private int queueSize = 10;
//    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);
//
//    public static void main(String[] args)  {
//        Qeuen test = new Qeuen();
//        Producer producer = test.new Producer();
//        Consumer consumer = test.new Consumer();
//
//        producer.start();
//        consumer.start();
//    }
//
//    class Consumer extends Thread{
//
//        @Override
//        public void run() {
//            consume();
//        }
//
//        private void consume() {
//            while(true){
//                try {
//                    queue.take();
//                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    class Producer extends Thread{
//
//        @Override
//        public void run() {
//            produce();
//        }
//
//        private void produce() {
//            while(true){
//                try {
//                    queue.put(1);
//                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}