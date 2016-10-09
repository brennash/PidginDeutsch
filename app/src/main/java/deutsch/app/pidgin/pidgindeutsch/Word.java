package deutsch.app.pidgin.pidgindeutsch;
import java.util.List;

public class Word {

    private double complexity    = 0.0;
    private String deWord        = null;
    private String enWord        = null;
    private List<String> deSound = null;
    private List<String> enSound = null;

    public Word(double complexity, String enWord, String deWord, List<String> deSound, List<String> enSound){
        this.complexity = complexity;
        this.enWord     = enWord;
        this.deWord     = deWord;
        this.enSound    = enSound;
        this.deSound    = deSound;
    }

    public double getComplexity() {
        return complexity;
    }

    public String getDeWord() {
        return deWord;
    }

    public String getEnWord() {
        return enWord;
    }

    public List<String> getDeSound() {
        return deSound;
    }

    public List<String> getEnSound() {
        return enSound;
    }

    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }

    public void setDeWord(String deWord) {
        this.deWord = deWord;
    }

    public void setEnWord(String enWord) {
        this.enWord = enWord;
    }

    public void setDeSound(List<String> deSound) {
        this.deSound = deSound;
    }

    public void setEnSound(List<String> enSound) {
        this.enSound = enSound;
    }

    public void increaseComplexity(){
        complexity += 0.01;
        if (complexity > 0.95){
            complexity = 0.95;
        }
    }

    public void decreaseComplexity(){
        complexity -= 0.01;
        if (complexity < 0.05){
            complexity = 0.05;
        }
    }

    @Override
    public String toString() {
        return "Word{" +
                "complexity=" + complexity +
                ", deWord='" + deWord + '\'' +
                ", enWord='" + enWord + '\'' +
                ", deSound=" + deSound +
                ", enSound=" + enSound +
                '}';
    }
}
