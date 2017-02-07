package ObserverDemo;

import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by marco on 16/11/13.
 */

public class ObserverDemo {

        public static void main(String[] args) {
//            ObserverA a = new ObserverA();
//            ObserverB b = new ObserverB();
//            ObserverC c = new ObserverC();
//
////            a.addObserver(b);
//            b.addObserver(a);
//            b.addObserver(c);
//            List<String> list = new ArrayList<>();
//            list.add("1");
//            list.add("1");
//
//            b.setData(list);
//
//            List<String> list2 = new ArrayList<>();
//            list2.add("1");
//            list2.add("1");
//            list2.add("1");
//
//            b.setData(list2);

//                for(String hh:list){
//                        String ssss = null;
//                        if(ssss!=null && ssss.equals("wq")){
//
//                        }


//                }
        }

        public static boolean isContainChinese(String str) {


                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher m = p.matcher(str);
                if (m.find()) {
                        return true;
                }
                return false;
        }


}

