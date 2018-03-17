public class Main {
    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();
        String startingURL = "https://github.com/joshuacassidy";
        webCrawler.findRelatedWebsites(startingURL);
    }
}
