import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marco on 17/2/6.
 */
public class ForDemo {

    private static void test(List<Integer> list) {
        long start = 0L;
        long end = 0L;
        // ============================== 迭代器：34ms(用了Iterator的遍历，Iterator是需要考虑同步的)
        start = System.currentTimeMillis();
        for (Integer i : list) {
                System.out.println(" 1 ");
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
        // ============================== 下标访问：30ms(ArrayList的效率要比LinkedList的效率要高，因为Array擅长随机访问)
        start = System.currentTimeMillis();
        for (int i=0;i<list.size();i++) {
            System.out.println(" 2 ");
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        // ============================== 优化的下标访问：29ms
        start = System.currentTimeMillis();
        for (int i=0,k=list.size();i<k;i++) {
            System.out.println(" 3 ");
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        // ============================== 效率较高
        //从数据结构角度分析,for循环适合访问顺序结构,可以根据下标快速获取指定元素.而Iterator 适合访问链式结构,因为迭代器是通过next()和Pre()来定位的.可以访问没有顺序的集合.
        start = System.currentTimeMillis();
        for (Iterator it = list.iterator(); it.hasNext();) {
            it.next();
            System.out.println(" 4 ");
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        // ============================== 当list需要romve元素时，用for循环需要同时下标跟着减否则出现错误。用如下方式 可以保持正确性，无论数据是否删除，iterator都会便利完所有元素
        // for (Iterator it = list.iterator(); it.hasNext();) {
        //      UserInfo user = (UserInfo)it.next();
        //      if(!StringUtil.equals(mDeviceId,user.deviceId)){
        //          it.remove();
        //      }
        //}
    }

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<150;i++){
            list.add(i);
        }
        test(list);
    }
}
