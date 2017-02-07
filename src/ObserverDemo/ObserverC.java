package ObserverDemo;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by marco on 16/11/13.
 */
public class ObserverC extends Observable implements Observer {

    @Override
    public void update(Observable object, Object arg) {
        ObserverB observerB = (ObserverB)object;

        System.out.println("observerB changed, the new value of observerB.data is222  " + observerB.list.size());

    }
}
