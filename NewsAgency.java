import java.util.ArrayList;
import java.util.List;

public class NewsAgency {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(String newsTitle) {
        System.out.println("\nBREAKING NEWS: " + newsTitle);
        for (Subscriber s : subscribers) {
            s.update(newsTitle);
        }
    }
}