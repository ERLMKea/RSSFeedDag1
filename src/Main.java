import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        JDBCWriter jdbcWriter = new JDBCWriter();
        boolean hasCon = jdbcWriter.setConnection();
        System.out.println("Har con hasCon=" + hasCon);

        if (hasCon) {
            int feedcount = jdbcWriter.countFeeds();
            System.out.println("Der var " + feedcount + " feeds");
        }
        ArrayList<RSSUrl> urls = RSSUrl.getNews();
        for (RSSUrl rss: urls) {
            System.out.println(rss);
            RSSFeedParser parser = new RSSFeedParser(rss.getRssurl());

            Feed feed = parser.readFeed();
            System.out.println("FEED læst=" + feed);

            int ii = jdbcWriter.writeFeed(rss, feed);
            System.out.println("WriteFeed kaldt i=" + ii);

            for (FeedMessage msg: feed.getMessages()) {
                //System.out.println(msg);
                int imsg = jdbcWriter.writeFeedMessages(rss, msg);
                System.out.println("feedmessage written imsg=" + imsg);
            }
        }
    } //end main
}
