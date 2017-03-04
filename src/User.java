import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

/**
 * Created by mikesayegh on 2017-03-04.
 */
public class User {
    String userName;
    float score;

    public User(String name){
        this.userName = name;
    }

    public String[] compileTweets() throws TwitterException{
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true).setOAuthConsumerKey("ME0BKDlR2hWaSGlc41lm2AGdE");
        cb.setDebugEnabled(true).setOAuthConsumerSecret("zLuOiqDd37LoAoXLO7EUZgjwDg6zzuR5qrLcyQlGiiztfRwwKA");
        cb.setDebugEnabled(true).setOAuthAccessToken("780890285727440896-xsL2jycwpbKzw8N1RvPR5O1t04eN2nE");
        cb.setDebugEnabled(true).setOAuthAccessTokenSecret("TIeQMGQrgguBsFGAbgqRM4BvL4R8dBSIt2E2BaGHapNsm");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();
        Paging page = new Paging(1,100);

        List<Status> users = twitter.getUserTimeline("mike_sayegh", page);

        for (Status user : users) {
            System.out.println(user + "1");
        }



        return null;
    }

}
