package deutsch.app.pidgin.pidgindeutsch;

import android.content.Context;

import com.google.gson.Gson;
import java.util.Random;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class WordStore {

    private ArrayList<Word> wordList;
    private Random randomGenerator;

    public WordStore(Context context, Language language){
        randomGenerator = new Random();
        String jsonString = loadJSONFromAsset(context, language);
        this.wordList = parseJSON(jsonString);
    }

    public Word getWord(double complexity){
        int index = randomGenerator.nextInt(wordList.size());
        Word word = (Word) wordList.get(index);

        if ((word.getComplexity() < (complexity-0.1)) || (word.getComplexity() > (complexity+0.1))){
            return getWord(complexity);
        } else {
            return word;
        }
    }

    private String loadJSONFromAsset(Context appContext, Language language) {
        String json = null;

        try {
            InputStream is = appContext.getAssets().open("de.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private ArrayList<Word> parseJSON(String jsonListString){
        ArrayList<Word> outputList = new ArrayList<Word>();
        StringTokenizer stringTokenizer = new StringTokenizer(jsonListString, "\n");
        Gson gson = new Gson();

        while (stringTokenizer.hasMoreElements()) {
            String jsonString = (String) stringTokenizer.nextElement();
            Word word = gson.fromJson(jsonString, Word.class);
            System.out.println("Found word: "+word.getDeWord());
            outputList.add(word);
        }

        return outputList;
    }

    public void incorrectWord(Word word){
        int index = wordList.indexOf(word);
        if (index != -1){
            word.increaseComplexity();
            wordList.add(index, word);
        }
    }

    /**
     * Function is called when a user guesses a word correctly.
     * This makes the complexity of the work reduce.
     * @param word The word which was guessed correctly.
     */
    public void correctWord(Word word){
        int index = wordList.indexOf(word);
        if (index != -1){
            word.decreaseComplexity();
            wordList.add(index, word);
        }
    }
}
