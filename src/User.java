import io.indico.api.utils.IndicoException;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private float score;
    double anger = 0, joy = 0, fear = 0, sadness = 0, surprise = 0;


    public User(String n) throws IndicoException{
        this.userName = n;
    }

    public String getUserName(){return this.userName;}
    public void setUserName(String n){this.userName = n;}
    public void setScore(float s){this.score = s;}
    ArrayList<Tweet> tweets = new ArrayList<>();
    ArrayList<Double> values = new ArrayList<>();
    ArrayList<List<Double>> emotions = new ArrayList<>();
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

    public void setEmotions() throws IOException, IndicoException{
        for (int i = 0; i < tweets.size(); i++){
            emotions.add(judge.judgeEmotions(tweets.get(i).getContent()));
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

    public void calculateEmotions(){
        for (int i = 0; i < emotions.size(); i++){
            List<Double> subString = emotions.get(i);
            anger = anger + subString.get(0);
            joy = joy + subString.get(1);
            fear = fear + subString.get(2);
            sadness = sadness + subString.get(3);
            surprise = surprise + subString.get(4);
        }
    }

    public double getAnger(){
        return anger;
    }

    public double getJoy(){
        return joy;
    }

    public double getFear(){
        return fear;
    }

    public double getSadness(){
        return sadness;
    }

    public double getSurprise(){
        return surprise;
    }
}