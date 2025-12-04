public class Website implements Subscriber {
    private final String siteName;

    public Website(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public void update(String newsTitle) {
        System.out.println(siteName + " updated: " + newsTitle);
    }
}