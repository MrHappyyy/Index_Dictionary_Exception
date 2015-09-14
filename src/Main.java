import javax.swing.text.html.parser.Entity;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException, DictionaryException {
        FileChooser fileChooser = new FileChooser();
        Map<String, HashMap<String, Integer>> dictionary = new TreeMap<String, HashMap<String, Integer>>();

        while(fileChooser.files == null) {
            System.out.print("");
        }

        System.out.println();

        DictionaryBuilder dictionaryBuilder = new DictionaryBuilder(fileChooser.files);


        /*dictionary = dictionaryBuilder.readFileAsList();

        for (Map.Entry entry : dictionary.entrySet()) {
            System.out.println("Слово: " + entry.getKey() + "   Документ: " + entry.getValue());
        }*/
    }
}
