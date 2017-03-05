import io.indico.api.utils.IndicoException;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.StrictMath.round;

public class User {
    private String userName;
    private double total;


    public User(String n) throws IndicoException{
        this.userName = n;
    }

    public String getUserName(){return this.userName;}
    public double getTotal(){return this.total;}
    public void setUserName(String n){this.userName = n;}
    public void setTotal(float s){this.total = s;}
    ArrayList<Tweet> tweets = new ArrayList<>();
    ArrayList<Double> values = new ArrayList<>();
    ArrayList<List<Double>> emotions = new ArrayList<>();
    double anger = 0, joy = 0, fear = 0, sadness = 0, surprise = 0;
    IndicoJudgement judge = new IndicoJudgement();

    public List<Tweet> compileTweets() throws TwitterException{
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
        return tweets;
    }

    public void setSentimentValues() throws IOException, IndicoException {
        for (int i = 0; i < tweets.size(); i++){
            values.add(judge.judgeSentiment(tweets.get(i).getContent()));
        }
    }

    public double calculate(){
        double total = 0;
        for (int i = 0; i < values.size(); i++){
            total = values.get(i) + total;
        }
        total = total/values.size();
        total = total * 100;
        total = round(total);

        return total;
    }
    public void calculateEmotions() throws IOException, IndicoException {
        for(int i = 0; i < tweets.size(); i++){
            emotions.add(judge.judgeEmotions(tweets.get(i).getContent()));
        }
        for(int i = 0; i < emotions.size(); i++){
            anger = anger + emotions.get(i).get(0);
            joy = joy + emotions.get(i).get(1);
            fear = fear + emotions.get(i).get(2);
            sadness = sadness + emotions.get(i).get(3);
            surprise = surprise + emotions.get(i).get(4);
        }
        anger = anger / emotions.size();
        joy = joy / emotions.size();
        fear = fear / emotions.size();
        sadness = sadness / emotions.size();
        surprise = surprise / emotions.size();
    }
    public String mostNegativeTweet() {
        String mostNegativeTweet = new String();
        double lowestScore = 1;

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) < lowestScore) {
                mostNegativeTweet = tweets.get(i).getContent();
                lowestScore = values.get(i);
            }
        }
        return mostNegativeTweet;
    }
    public String mostPositiveTweet() {
        String mostPositiveTweet = "";
        double highestScore = 0;

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) > highestScore) {
                mostPositiveTweet = tweets.get(i).getContent();
                highestScore = values.get(i);
            }
        }
        return mostPositiveTweet;
    }

}
