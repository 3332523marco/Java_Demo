package ObserverDemo;

/**
 * Created by marco on 16/11/13.
 */
//ObserverA.java
import java.util.Observable;
import java.util.Observer;

public class ObserverA extends Observable implements Observer {

    @Override
    public void update(Observable object, Object arg) {
        ObserverB observerB = (ObserverB)object;
        System.out.println("observerB changed, the new value of observerB.data is " + observerB.list.size()+"  sss  "+arg);
//        this.setChanged();
//        this.notifyObservers();
    }
}
