import twitter4j.Status;
import twitter4j.Twitter;

import java.util.List;

public class Test {
    public static void main(String[] args){
        Twitter twitter = new Twitter(twitterID,twitterPassword);
        List statuses = twitter.getFriendsTimeline();
        System.out.println("Showing friends timeline.");
        for(int i=0; i < statuses.size() ; i++) {
            Status status = (Status)statuses.get(i);
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }
    }
}
