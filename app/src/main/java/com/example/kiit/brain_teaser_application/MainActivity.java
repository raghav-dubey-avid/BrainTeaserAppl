package com.example.kiit.brain_teaser_application;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    TextView resultTextView;
    TextView sumTextView;
    TextView pointsTextView;
    TextView timeTextView;
    ConstraintLayout gameConstraintLayout;

    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationofCorrectAnswer;
    int score = 0;
    int numberofQuestions = 0;

    public void playAgain(View view){

        score = 0;
        numberofQuestions = 0;
        timeTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestions();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {

                timeTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timeTextView.setText("0s");
                resultTextView.setText("Your Score"+ "" + Integer.toString(score) + "/" + Integer.toString(numberofQuestions));
            }
        }.start();
    }

    public void generateQuestions() {

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationofCorrectAnswer = rand.nextInt(4);

        answer.clear(); //Arraylist is added every time in our previous question so it must be removed

        for (int i = 0; i < 4; i++) {

            int incorrectAnswer;

            if (i == locationofCorrectAnswer) {
                answer.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);

                //if the incorrect answer comes out be the correct one
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answer.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationofCorrectAnswer))) {

            score++;
            resultTextView.setText("CORRECT");
        } else {
            resultTextView.setText("WRONG!");
        }
        numberofQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofQuestions));
        generateQuestions();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        gameConstraintLayout = (ConstraintLayout)findViewById(R.id.gameConstraintLayout);


    }
}