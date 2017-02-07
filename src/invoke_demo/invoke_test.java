package invoke_demo;

/**
 * Created by marco on 16/10/21.
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class invoke_test {
    public static void main(String[] args) {
//        getInfo();
//        callSpuerMethod();
//        callCurrentMethod();
        callOtherMethod();
    }

//    static void getInfo() {
//        Man r = new Man();
//        Class<?> temp = r.getClass();
//        try {
//            System.out.println("反射类中所有公有的属性");
//            Field[] fb = temp.getFields();
//            for (int j = 0; j < fb.length; j++) {
//                Class<?> cl = fb[j].getType();
//                System.out.println("fb:" + cl + "___" + fb[j].getName());
//            }
//            System.out.println("反射类中所有的属性");
//            Field[] fa = temp.getDeclaredFields();
//            for (int j = 0; j < fa.length; j++) {
//                Class<?> cl = fa[j].getType();
//                System.out.println("fa:" + cl + "____" + fa[j].getName());
//            }
//
//            System.out.println("反射类中所有的方法");
//            Method[] fm = temp.getMethods();
//            for (int i = 0; i < fm.length; i++) {
//                System.out.println("fm:" + fm[i].getName() + "____"
//                        + fm[i].getReturnType().getName());
//            }
//
//            System.out.println("反射类中所有的接口");
//            Class<?>[] fi = temp.getInterfaces();
//            for (int i = 0; i < fi.length; i++) {
//                System.out.println("fi:" + fi[i].getName());
//            }
//
//            System.out.println("反射类中私有属性的值");
//            Field f = temp.getDeclaredField("var1");
//            f.setAccessible(true);
//            String i = (String) f.get(r);
//            System.out.println(i);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    /**
     * 修复父类变量，调用父类方法
     */
//    static void callSpuerMethod() {
//        Man r = new Man();
//        try {
//            // 修改私有变量；
//            Field f = r.getClass().getSuperclass().getDeclaredField("age");
//            f.setAccessible(true);
//            f.set(r, 20);
//
//            // 调用私有方法,必须要用getDeclaredMethod，而不能用getMethod；
//            Method mp = r.getClass().getSuperclass()
//                    .getDeclaredMethod("priMethod", Integer.class);
//            mp.setAccessible(true);
//            mp.invoke(r, 18);
//
//            // 调用隐藏方法
//            Method m = r.getClass().getSuperclass()
//                    .getMethod("hideMethod", String.class);
//            m.setAccessible(true);
//            m.invoke(r, "Jerome");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//
//    /**
//     * 修复子类变量，调用子类方法
//     */
//    static void callCurrentMethod() {
//        Man r = new Man();
//        try {
//            // 修改私有变量；
//            Field f = r.getClass().getDeclaredField("age");
//            f.setAccessible(true);
//            f.set(r, 20);
//
//            // 调用私有方法,必须要用getDeclaredMethod，而不能用getMethod；
//            Method mp = r.getClass().getDeclaredMethod("getName");
//            mp.setAccessible(true);
//            mp.invoke(r);
//
//            // 调用隐藏私有方法
//            Method m = r.getClass().getDeclaredMethod("getAge");
//            m.setAccessible(true);
//            m.invoke(r);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 用Class.forName加载类及实例化
     */
    static void callOtherMethod() {
        try {
            // Class.forName(xxx.xx.xx) 返回的是一个类, .newInstance() 后才创建一个对象
            // Class.forName(xxx.xx.xx) 的作用是要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段

            Class<?> cl = Class.forName("invoke_demo.Man");

            /**
             * 构造方法不带参数 直接 newInstance创建对象
             */
//            Object obj = cl.newInstance();

            /**
             * 构造方法带参数
             */
            Constructor<?>[] cons = cl.getConstructors();
            Constructor<?> con = cons[0];

            Object obj = con.newInstance("aa");

            // 修改私有变量；
            Field f = cl.getDeclaredField("age");
            f.setAccessible(true);
            f.set(obj, 20);

            // 调用私有方法,必须要用getDeclaredMethod，而不能用getMethod；
            Method mp = cl.getDeclaredMethod("getName");
            mp.setAccessible(true);
            mp.invoke(obj);

            // 调用隐藏私有方法
            Method mp2 = cl.getDeclaredMethod("getAge");
            mp2.setAccessible(true);
            mp2.invoke(obj);

            //调用带参数的隐藏方法
            Method mp3 = cl.getDeclaredMethod("paramsMethod", String.class);
            mp3.invoke(obj, "hello");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}