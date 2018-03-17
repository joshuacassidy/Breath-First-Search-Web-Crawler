import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private Queue<String> queue;
    private List<String> discoveredWebsites;

    public WebCrawler() {
        this.queue = new LinkedList<>();
        this.discoveredWebsites = new ArrayList<>();
    }

    public void findRelatedWebsites(String root) {
        queue.add(root);
        discoveredWebsites.add(root);

        for (int i = 1; !queue.isEmpty(); i++) {
            String page = this.queue.poll();
            System.out.println("Visiting URL No. " + i+": " + page);
            String html = parsePage(page);
            String regEx = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String actualURL = matcher.group();
                if (!discoveredWebsites.contains(actualURL)) {
                    discoveredWebsites.add(actualURL);
                    System.out.println("A related website has been found: " + actualURL);
                    queue.add(actualURL);
                }
            }
        }
        System.out.println("Processed all related websites");

    }

    public String parsePage(String page) {
        String html = "";
        try {
            URL url = new URL(page);
            BufferedReader readContents = new BufferedReader(new InputStreamReader(url.openStream()));
            for (String inputLine; (inputLine = readContents.readLine()) != null; html+=inputLine);
            readContents.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return html;
    }

}
