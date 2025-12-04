public class Main {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        Subscriber alice = new PhoneUser("Alice");
        Subscriber bob   = new PhoneUser("Bob");
        Subscriber cnn   = new Website("CNN.com");
        Subscriber bbc   = new Website("BBC News");

        // Subscribe
        agency.subscribe(alice);
        agency.subscribe(bob);
        agency.subscribe(cnn);
        agency.subscribe(bbc);

        // First news
        agency.publish("Earthquake Hits Capital City");

        // Bob unsubscribes
        agency.unsubscribe(bob);

        // Second news
        agency.publish("Peace Agreement Signed");

        // New subscriber joins later
        Subscriber lateUser = new PhoneUser("Charlie");
        agency.subscribe(lateUser);

        // Final news
        agency.publish("Stock Market Surges 10%");
    }
}