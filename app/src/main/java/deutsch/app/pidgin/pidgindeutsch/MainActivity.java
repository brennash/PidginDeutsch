package deutsch.app.pidgin.pidgindeutsch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

        private double complexity  = 0.1;
        private int guessedCorrect = 0;
        private int guessedWrong   = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            View view = this.findViewById(android.R.id.content);
            Language language = new Language(Language.DE);
            Context context = this.getBaseContext();
            final WordStore wordStore = new WordStore(context, language);

            view.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

                @Override
                public void onClick() {
                    super.onClick();

                }

                @Override
                public void onDoubleClick() {
                    super.onDoubleClick();
                    // your on onDoubleClick here
                }

                @Override
                public void onLongClick() {
                    super.onLongClick();
                    // your on onLongClick here
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
                    TextView text = (TextView) findViewById(R.id.textView2);
                    String wordText = wordStore.getWord(0.0);
                    //String wordText = word.getDeWord();
                    text.setText(wordText);
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    TextView text = (TextView) findViewById(R.id.textView2);
                    String wordText = wordStore.getWord(0.0);
                    //String wordText = word.getDeWord();
                    text.setText(wordText);
                }
            });
        }



}
