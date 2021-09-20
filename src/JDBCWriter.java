import java.sql.*;

public class JDBCWriter {

    private Connection connection;

    public boolean setConnection() {
        final String url = "jdbc:mysql://localhost:3306/rssfeedv1?serverTimezone=UTC";
        boolean res = false;
        try {
            connection = DriverManager.getConnection(url, "vivi", "x");
            res = true;
        } catch (SQLException err) {
            System.out.println("Vi fik IKKE connection err=" + err.getMessage());
        }
        return res;
    }

    public int countFeeds() {
        String str = "select count(*) from feeds";
        PreparedStatement preparedStatement;
        int res = -1;
        try {
            preparedStatement = connection.prepareStatement(str);
            ResultSet resset = preparedStatement.executeQuery();
            if (resset.next()) {
                //String str2 = "" + resset.getObject(1);
                //res = Integer.parseInt(str2);
                res =resset.getInt(1);
            }
        } catch (SQLException err) {
            System.out.println("Fejl i count err=" + err.getMessage());
        }
        return res;
    }

    public int writeFeed(RSSUrl rssUrl, Feed feed) {
        //insert into feeds(feedid, title, link) values (5, "det hagler i dag rss", "eb.sner.rss");
        String insstr = "insert into feeds(feedid, feedurl, title, link) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        int rowcount = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr);
            preparedStatement.setInt(1, rssUrl.getFeedid());
            preparedStatement.setString(2, rssUrl.getRssurl());
            preparedStatement.setString(3, feed.getTitle());
            preparedStatement.setString(4, feed.getLink());
            int ii = preparedStatement.executeUpdate();
            rowcount += ii;
        } catch (SQLException err) {
            System.out.println("FEJL i insert feed err=" + err.getMessage());
        }
        return rowcount;
    }

    public int writeFeedMessages(RSSUrl rssUrl, FeedMessage feedMessage) {
        //insert into feedmessages(feedid, title, description, guid) values (1, "skal slettes", "slet mig", "jeg er unik");
        String insstr = "insert into feedmessages(feedid, guid, title, description) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        int rowcount = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr);
            preparedStatement.setInt(1, rssUrl.getFeedid());
            preparedStatement.setString(2, feedMessage.getGuid());
            preparedStatement.setString(3, feedMessage.getTitle());
            preparedStatement.setString(4, feedMessage.getDescription());
            int ii = preparedStatement.executeUpdate();
            rowcount += ii;
        } catch (SQLException err) {
            System.out.println("FEJL i insert feedmessage err=" + err.getMessage());
        }
        return rowcount;
    }


}


