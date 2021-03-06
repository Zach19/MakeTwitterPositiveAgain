import io.indico.api.utils.IndicoException;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikesayegh on 2017-03-04.
 */
public class User {
    String userName;
    ArrayList<Tweet> tweets = new ArrayList<>();
    ArrayList<Double> values = new ArrayList<>();
    IndicoJudgement judge = new IndicoJudgement();
    float score;

    public User(String name) throws IndicoException {
        this.userName = name;
    }

    public void compileTweets() throws TwitterException, IOException, IndicoException {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true).setOAuthConsumerKey("ME0BKDlR2hWaSGlc41lm2AGdE");
        cb.setDebugEnabled(true).setOAuthConsumerSecret("zLuOiqDd37LoAoXLO7EUZgjwDg6zzuR5qrLcyQlGiiztfRwwKA");
        cb.setDebugEnabled(true).setOAuthAccessToken("780890285727440896-xsL2jycwpbKzw8N1RvPR5O1t04eN2nE");
        cb.setDebugEnabled(true).setOAuthAccessTokenSecret("TIeQMGQrgguBsFGAbgqRM4BvL4R8dBSIt2E2BaGHapNsm");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();
        Paging page = new Paging(1,100);

        List<Status> users = twitter.getUserTimeline(userName, page);

        for (Status user : users) {
            String[] temp = user.toString().split(",");
            if(temp[2].length()==0){
                continue;

            }
            else {
                String text = temp[2].substring(7, temp[2].length() - 1);
                String date = temp[0].substring(25, temp[0].length());
                tweets.add(new Tweet(text, date));
            }
        }
    }
    public void setSentimentValues() throws IOException, IndicoException {
        for (int i = 0; i < tweets.size(); i++){
            values.add(judge.judge(tweets.get(i).getContent()));
        }
    }
    public double calculate(){
        double total = 0;
        for (int i = 0; i < values.size(); i++){
            total = values.get(i) + total;
        }
        total = total/values.size();
        total = total * 100;

        return total;
    }

}
