package viraj.example.com.quiz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int number1, number2, numberOfCorrectAnswer=0, numberOfWrongAnswer=0;
    private int answer1 = 0, answer2 = 0, CORRECT_ANSWER = 0, score = 0;
    private Button submit;
    private TextView setQuestion, testScore, correctScore, inCorrectScore;
    private RadioGroup answers;
    private RadioButton option1, option2, option3;
    private MediaPlayer correctAnswer, wrongAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        correctAnswer = MediaPlayer.create(MainActivity.this, R.raw.correct);
        wrongAnswer = MediaPlayer.create(MainActivity.this, R.raw.wrong);

        testScore = (TextView) findViewById(R.id.score);
        correctScore = (TextView) findViewById(R.id.correctScore);
        inCorrectScore = (TextView) findViewById(R.id.incorrectScore);
        submit = (Button) findViewById(R.id.submit);
        setQuestion = (TextView) findViewById(R.id.question);
        answers = (RadioGroup) findViewById(R.id.answers);
        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);

        testScore.setText(String.valueOf(score));
        setNewQuestion();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = answers.getCheckedRadioButtonId();
                switch (checked){
                    case R.id.option1:
                        startMusic(correctAnswer);
                        numberOfCorrectAnswer++;
                        correctScore.setText(String.valueOf(numberOfCorrectAnswer));
                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        score += 10;
                        testScore.setText(String.valueOf(score));
                        setNewQuestion();
                        break;

                    case R.id.option2:
                        startMusic(wrongAnswer);
                        numberOfWrongAnswer++;
                        inCorrectScore.setText(String.valueOf(numberOfWrongAnswer));
                        Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        score -= 10;
                        testScore.setText(String.valueOf(score));
                        setNewQuestion();
                        break;

                    case R.id.option3:
                        startMusic(wrongAnswer);
                        numberOfWrongAnswer++;
                        inCorrectScore.setText(String.valueOf(numberOfWrongAnswer));
                        Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        score -= 10;
                        testScore.setText(String.valueOf(score));
                        setNewQuestion();
                        break;
                }
            }
        });


    }


    public void setNewQuestion() {
        answers.clearCheck();
        setQuestion();
        setOption();
        setOptionToCheckBox();

    }

    private void setOptionToCheckBox() {
        option1.setText(String.valueOf(CORRECT_ANSWER));
        option2.setText(String.valueOf(answer1));
        option3.setText(String.valueOf(answer2));
    }


    private void setOption() {
        answer1 = checkIfTheOptionIsRepetitive(getRandomNumber(100));
        answer2 = checkIfTheOptionIsRepetitive(getRandomNumber(100));
    }

    private void setQuestion() {
        number1 = getRandomNumber(100);
        number2 = getRandomNumber(100);
        setQuestion.setText("What is "+number1 + " + " + number2);
        CORRECT_ANSWER = number1 + number2;
    }

    private int checkIfTheOptionIsRepetitive(int setAnswer) {
        if (setAnswer == CORRECT_ANSWER || setAnswer == answer1 || setAnswer == answer2){
            checkIfTheOptionIsRepetitive(getRandomNumber(50));
        }
        return setAnswer;
    }


    private void startMusic(MediaPlayer music) {
        music.start();
    }



    private int getRandomNumber(int limit) {
        return (int) (Math.random() * limit + 1);
    }

}

