
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class DictionaryBuilder {
    private File[] files;
    private Map<String, HashMap<String, Integer>> dictionary = new TreeMap<String, HashMap<String, Integer>>();
    private int ammautcount = 0;

    public DictionaryBuilder(File[] files) throws IOException, DictionaryException {
        this.files = files;
        readFileAsList();
    }

    public void readFileAsList() throws IOException, DictionaryException {
        BufferedReader br;
        StringTokenizer tz;

        for (int i = 0; i < files.length; i++) {
            List<String> words = new ArrayList<String>();
            String wordLine = "";
            String[] word;

            Scanner file = new Scanner(new FileReader(files[i]));

            while (file.hasNext())
                wordLine += file.nextLine().toLowerCase();
            file.close();

            word = wordLine.split("[?!*,-.\"_\"\\;/:)(+\\s]+");

            for (int j = 0; j < word.length; j++) {
                words.add(word[j]);
            }

            StopWords stopWords = new StopWords();
            words = StopWords.removeStopWords(words);

            /*for (int j = 0; j < words.size(); j++) {
                System.out.print(words.get(j) + "/");
            }*/
            write(words, files[i].getName());
        }
    }

    private void write(List<String> words, String documentsName) throws DictionaryException {

        for (int i = 0; i < words.size(); i++) {
            HashMap<String, Integer> hashMap = new HashMap<String,Integer>();

            if (dictionary.containsKey(words.get(i))) {
                hashMap = dictionary.get(words.get(i));
                int countWords;

                if (hashMap.containsKey(documentsName)) {
                    countWords = hashMap.get(documentsName);
                    countWords += 1;
                    hashMap.remove(documentsName);
                    hashMap.put(documentsName, countWords);
                } else {
                    hashMap.put(documentsName, 1);
                }
                dictionary.remove(words.get(i));
                dictionary.put(words.get(i), hashMap);
            } else {
                hashMap.put(documentsName, 1);
                dictionary.put(words.get(i), hashMap);
            }
        }
        writeToFile();
    }

    public void writeToFile() throws DictionaryException{
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyy hh:mm");

            String fileName = simpleDateFormat.format(date) + " Dictionary.txt";
            File file = new File(fileName);

            file.createNewFile();

            PrintWriter pout = new PrintWriter(file.getAbsoluteFile());

            try {
                String string;

                for (Map.Entry entry : dictionary.entrySet()) {
                    string = "Слово: " + entry.getKey() + "   Документ: " + entry.getValue() + "\n";
                    pout.println(string);
                    ammautcount += 1;
                }

                pout.print("\n Количество слов словаре:" + ammautcount);
            } finally {
                pout.close();
            }
        }catch (IOException e) {
            throw new DictionaryException(e);
        }
    }
}