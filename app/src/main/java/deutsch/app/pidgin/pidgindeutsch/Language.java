package deutsch.app.pidgin.pidgindeutsch;

/**
 * Created by brennash on 09/10/2016.
 */

public class Language {

    public final static String DE = "de.json";
    private String selectedLanguage = null;

    public Language(String language){
        if (language.equalsIgnoreCase(DE) || language.equalsIgnoreCase("GERMAN")){
            selectedLanguage = DE;
        }
    }

    public String getLanguage(String language){
        return selectedLanguage;
    }
}
