import io.indico.api.utils.IndicoException;

import java.io.IOException;

/**
 * Created by mikesayegh on 2017-03-04.
 */
public class Tweet {
    private String content;
    private String date;
    private double score;
    IndicoJudgement indico = new IndicoJudgement();

    public Tweet() throws IndicoException {
    }

    public Tweet(String currentContent, String currentDate) throws IndicoException, IOException {
        this.content = currentContent;
        this.date = currentDate;
        this.score = indico.judge(currentContent);

    }

    public String getContent(){return this.content;}
    public String getDate(){return this.date;}
    public double getScore() {
        return this.score;
    }





}
