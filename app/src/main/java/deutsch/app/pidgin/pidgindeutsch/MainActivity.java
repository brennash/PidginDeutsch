package deutsch.app.pidgin.pidgindeutsch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;


public class MainActivity extends AppCompatActivity {

        private double complexity  = 0.1;
        private int guessedCorrect = 0;
        private int guessedWrong   = 0;
        private boolean initGame   = false;
        private Word word          = null;
        private WordStore wordStore;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            View view = this.findViewById(android.R.id.content);
            Language language = new Language(Language.DE);
            Context context = this.getBaseContext();
            wordStore = new WordStore(context, language);

            view.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

                @Override
                public void onClick() {
                    super.onClick();
                    if (!initGame) {
                        updateDisplay();
                        initGame = true;
                    }
                }

                @Override
                public void onDoubleClick() {
                    super.onDoubleClick();
                    // your on onDoubleClick here
                }

                @Override
                public void onLongClick() {
                    super.onLongClick();
                    if (!initGame) {
                        updateDisplay();
                        initGame = true;
                    } else {
                        showTranslation();
                    }
                }

                @Override
                public void onSwipeUp() {
                    super.onSwipeUp();
                    // your swipe up here
                }

                @Override
                public void onSwipeDown() {
                    super.onSwipeDown();
                    // your swipe down here.
                }

                @Override
                public void onSwipeLeft() {
                    super.onSwipeLeft();
                    guessedWrong();
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    guessedCorrect();
                }
            });
        }

        private void updateDisplay(){
            TextView textView = (TextView) findViewById(R.id.word_field);
            Word word = wordStore.getWord(complexity);
            String wordText = word.getDeWord();
            textView.setText(wordText);
            guessedCorrect = 0;
            guessedWrong   = 0;

            String correctText   = "Correct: "+Integer.toString(guessedCorrect);
            String incorrectText = "InCorrect: "+Integer.toString(guessedCorrect);
            TextView correctField = (TextView) findViewById(R.id.correct_field);
            TextView incorrectField = (TextView) findViewById(R.id.incorrect_field);
            correctField.setText(correctText);
            incorrectField.setText(incorrectText);

            try {
                Thread.sleep(3000);
                String answerText = word.getEnWord();
                textView.setText(answerText);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

        private void guessedCorrect(){
            TextView textView = (TextView) findViewById(R.id.word_field);
            Word word = wordStore.getWord(complexity);
            String wordText = word.getDeWord();
            textView.setText(wordText);
            decreaseComplexity(word);
            guessedCorrect += 1;

            String correctText   = "Correct: "+Integer.toString(guessedCorrect);
            String incorrectText = "InCorrect: "+Integer.toString(guessedWrong);
            TextView correctField = (TextView) findViewById(R.id.correct_field);
            TextView incorrectField = (TextView) findViewById(R.id.incorrect_field);
            correctField.setText(correctText);
            incorrectField.setText(incorrectText);

            try {
                Thread.sleep(5000);
                String answerText = word.getEnWord();
                textView.setText(answerText);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

        private void guessedWrong(){
            TextView textView = (TextView) findViewById(R.id.word_field);
            word = wordStore.getWord(complexity);
            String wordText = word.getDeWord();
            textView.setText(wordText);
            increaseComplexity(word);
            guessedWrong += 1;

            String correctText   = "Correct: "+Integer.toString(guessedCorrect);
            String incorrectText = "InCorrect: "+Integer.toString(guessedWrong);
            TextView correctField = (TextView) findViewById(R.id.correct_field);
            TextView incorrectField = (TextView) findViewById(R.id.incorrect_field);
            correctField.setText(correctText);
            incorrectField.setText(incorrectText);

            try {
                Thread.sleep(5000);
                String answerText = word.getEnWord();
                textView.setText(answerText);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

        private void showTranslation(){
            TextView textView = (TextView) findViewById(R.id.word_field);
            String wordText = word.getDeWord();
            textView.setText(wordText);
        }

        private void increaseComplexity(Word word){
            complexity += 0.01;
            wordStore.correctWord(word);

            if (complexity > 0.95){
                complexity = 0.95;
            }
        }

        private void decreaseComplexity(Word word){
            complexity -= 0.01;
            wordStore.incorrectWord(word);
            if (complexity < 0.05){
                complexity = 0.05;
            }
        }

}
