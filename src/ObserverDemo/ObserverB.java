package ObserverDemo;

/**
 * Created by marco on 16/11/13.
 */
//ObserverB.java
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ObserverB extends Observable implements Observer {

    List<String> list;
    @Override
    public void update(Observable object, Object arg) {
        System.out.println("ObserverB found that ObserverA changed...");
    }

    public void setData(List<String> list){
        this.list = list;
        this.setChanged();
        this.notifyObservers(121);
    }
}