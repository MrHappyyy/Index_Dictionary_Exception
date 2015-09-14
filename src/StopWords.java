import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StopWords {
    private static List<String> words = new ArrayList<String>();

    static {
        try {
            InputStream is = new FileInputStream("stopwords.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            try {
                while((line = br.readLine()) != null){
                    words.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<String> removeStopWords(List<String> tokens){
        List<String> res = new ArrayList<String>();
        String stopWords = "Стоп слова:/";

        for(String token: tokens){

            if(words.contains(token)){
                stopWords += token +"/";
            } else {
                res.add(token);
            }
        }
        //System.out.println(stopWords);
        return res;
    }
}
