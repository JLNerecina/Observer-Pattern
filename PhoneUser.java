public class PhoneUser implements Subscriber {
    private final String name;

    public PhoneUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String newsTitle) {
        System.out.println("Notification → " + name + ": " + newsTitle);
    }
}