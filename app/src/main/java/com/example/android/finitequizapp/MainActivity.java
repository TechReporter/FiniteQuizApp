package com.example.android.finitequizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdt;
    Button submitButton;
    ProgressBar progressBar;
    ArrayList<QuestionModal> questionModalArrayList;

    int currentPosition = 0;
    int numberOfCorrectAnswser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionCountLabel = findViewById(R.id.noQuestion);
        questionLabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);

        answerEdt = findViewById(R.id.answer);
        submitButton = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progress);

        questionModalArrayList = new ArrayList<>();

        setUpQuestion();

        setData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkanswer();

            }
        });
    }


    public void checkanswer() {

        String answerString = answerEdt.getText().toString();
        if (answerString.equalsIgnoreCase(questionModalArrayList.get(currentPosition).getAnswer())) {
            numberOfCorrectAnswser++;

            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("Right Answer")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            currentPosition++;

                            setData();
                            answerEdt.setText("");
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        } else {
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong Answer")
                    .setContentText("The right answer is : " + questionModalArrayList.get(currentPosition).getAnswer())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();

                            currentPosition++;

                            setData();
                            answerEdt.setText("");
                        }
                    })
                    .show();
        }

        int x = ((currentPosition + 1) * 100) / questionModalArrayList.size();
        progressBar.setProgress(x);
    }

    public void setUpQuestion() {
        questionModalArrayList.add(new QuestionModal("What type of galaxy is the most common in the universe? ", "elliptical galaxies"));
        questionModalArrayList.add(new QuestionModal("20 % of 2 is equal to  ? ", "0.4"));
        questionModalArrayList.add(new QuestionModal("Who invented Java ? ", "james gosling"));
        questionModalArrayList.add(new QuestionModal("which is the next android version ? ", "android p"));
        questionModalArrayList.add(new QuestionModal("What is the Initial release year of Android  ? ", "2008"));
    }

    public void setData() {


        if (questionModalArrayList.size() > currentPosition) {

            questionLabel.setText(questionModalArrayList.get(currentPosition).getQuestionString());

            scoreLabel.setText("Score :" + numberOfCorrectAnswser + "/" + questionModalArrayList.size());
            questionCountLabel.setText("Question No : " + (currentPosition + 1));


        } else {


            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("You have successfully completed the quiz")
                    .setContentText("Your score is : " + numberOfCorrectAnswser + "/" + questionModalArrayList.size())
                    .setConfirmText("Restart")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismissWithAnimation();
                            currentPosition = 0;
                            numberOfCorrectAnswser = 0;
                            progressBar.setProgress(0);
                            setData();
                        }
                    })
                    .setCancelText("Close")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismissWithAnimation();
                            finish();
                        }
                    })
                    .show();

        }

    }


}