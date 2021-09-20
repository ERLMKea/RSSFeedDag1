import java.util.ArrayList;

public class RSSUrl {

    private int feedid;
    private String rssurl;

    public static ArrayList<RSSUrl> getNews() {
        ArrayList<RSSUrl> rss = new ArrayList<>();
        rss.add(new RSSUrl(1, "https://ekstrabladet.dk/rssfeed/musik/"));
        rss.add(new RSSUrl(2, "https://ekstrabladet.dk/rssfeed/kendte/"));
        rss.add(new RSSUrl(3, "http://rss.cnn.com/rss/edition_space.rss"));
        return rss;
    }

    public RSSUrl(int feedid, String rssurl) {
        this.feedid = feedid;
        this.rssurl = rssurl;
    }

    public int getFeedid() {
        return feedid;
    }

    public void setFeedid(int feedid) {
        this.feedid = feedid;
    }

    public String getRssurl() {
        return rssurl;
    }

    public void setRssurl(String rssurl) {
        this.rssurl = rssurl;
    }

    @Override
    public String toString() {
        return "RSSUrl{" +
                "feedid=" + feedid +
                ", rssurl='" + rssurl + '\'' +
                '}';
    }
}
