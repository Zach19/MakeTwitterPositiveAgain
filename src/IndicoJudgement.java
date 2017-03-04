import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;
import java.io.IOException;
import java.util.List;

public class IndicoJudgement {
    Indico indico;
    public IndicoJudgement() throws IndicoException {
        indico = new Indico("f759097f01407bd1302f6caad8cf62d0");
    }
    public List<Double> judge(List<Tweet> listOfTweets) throws IOException, IndicoException {
        List<Double> numbers = null;
        for (int i = 0; i < listOfTweets.size(); i++){
            IndicoResult single = indico.sentiment.predict(
                    listOfTweets.get(i).getContent()
            );
            numbers.add(single.getSentiment());
        }
        return numbers;
    }
}
