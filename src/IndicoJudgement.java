import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.text.Emotion;
import io.indico.api.utils.IndicoException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndicoJudgement {
    Indico indico;
    public IndicoJudgement() throws IndicoException {
        indico = new Indico("f759097f01407bd1302f6caad8cf62d0");
    }
    public Double judgeSentiment(String content) throws IOException, IndicoException {
        try {
            IndicoResult single = indico.sentiment.predict(content);
            return single.getSentiment();
        }catch(io.indico.api.utils.IndicoException e){
            System.out.println("error in grabbing sentiment for the phrase " + content);
            return .5;
        }

    }
    public Double judgeReception(String content) throws IOException, IndicoException {
        IndicoResult single = indico.twitterEngagement.predict(content);
        return single.getTwitterEngagement();
    }
    public List<Double> judgeEmotions(String content) throws IOException, IndicoException{
        ArrayList<Double> emotions = new ArrayList<>();
        ArrayList<Double> defaultList = new ArrayList<>();
        defaultList.add(.5);
        defaultList.add(.5);
        defaultList.add(.5);
        defaultList.add(.5);
        defaultList.add(.5);
        try {
            Map<Emotion, Double> results = indico.emotion.predict(content).getEmotion();
            for (Map.Entry<Emotion, Double> e : results.entrySet()){
                emotions.add(e.getValue());
            }
            return emotions;
        }catch(io.indico.api.utils.IndicoException e){
            System.out.println("error in grabbing emotions for the phrase " + content);
            return defaultList;
        }


    }
}
