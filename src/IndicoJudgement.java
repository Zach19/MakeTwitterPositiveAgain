import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import java.io.IOException;

public class IndicoJudgement {
    Indico indico;
    public IndicoJudgement() throws IndicoException {
        indico = new Indico("f759097f01407bd1302f6caad8cf62d0");
    }
    public Double judge(String content) throws IOException, IndicoException {
        System.out.println(content);
        try {
            IndicoResult single = indico.sentiment.predict(content);
            return single.getSentiment();
        }catch(io.indico.api.utils.IndicoException e){
            System.out.println("error");
        }
        return .5;
    }
}
