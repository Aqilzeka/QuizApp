package com.aqil.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{


    private final String SCORE_KEY = "SORCE";
    private final String INDEX_KEY = "INDEX";
    private TextView mTxtQuestion;
    private Button btnFalse;
    private Button btnTrue;

    private int mQuestionInex;
    private int mQuizQuestion;

    private ProgressBar mProgressBar;
    private TextView mQuizStatusTextView;

    private int mUserScore;

    private QuizModel[] questionCollection = new QuizModel[]{

            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, true),
            new QuizModel(R.string.q5, false),
            new QuizModel(R.string.q6, true),
            new QuizModel(R.string.q7, false),
            new QuizModel(R.string.q8, true),
            new QuizModel(R.string.q9, false),
            new QuizModel(R.string.q10, true),



    };


    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){

            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionInex = savedInstanceState.getInt(INDEX_KEY);

        } else {
            mUserScore = 0;
            mQuestionInex = 0;
        }


         Toast.makeText(getApplicationContext(), "Oncreated metod", Toast.LENGTH_LONG).show();

         mTxtQuestion =findViewById(R.id.txtquestion);

         QuizModel q1 = questionCollection[mQuestionInex];

         mQuizQuestion = q1.getmQuestion();

         mTxtQuestion.setText(mQuizQuestion);

         mProgressBar = findViewById(R.id.quizPB);
         mQuizStatusTextView = findViewById(R.id.txtQuizStats);
        mQuizStatusTextView.setText(mUserScore + "");

         btnTrue =findViewById(R.id.trueButton);
         btnFalse = findViewById(R.id.wrongButton);


        View.OnClickListener myClickListener = new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.trueButton:
                        evaluatedUsersAnswer(true);
                        changeQuestionOnButtonClick();

                        break;

                    case R.id.wrongButton:
                        evaluatedUsersAnswer(false);
                        changeQuestionOnButtonClick();
                        break;

                }
            }
        };


        btnTrue.setOnClickListener(myClickListener);
        btnFalse.setOnClickListener(myClickListener);

    }

    private void changeQuestionOnButtonClick(){
        mQuestionInex = (mQuestionInex + 1) % 10;
        if (mQuestionInex == 0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finshed");
            quizAlert.setMessage("Your score is " + mUserScore);
            quizAlert.setPositiveButton("Finsh the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();

                }
            });
            quizAlert.show();
        }
        mQuizQuestion = questionCollection[mQuestionInex].getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatusTextView.setText(mUserScore + "");




    }

    private void evaluatedUsersAnswer(boolean userGuess){

        boolean currentQuestionAnswer = questionCollection[mQuestionInex].ismAnswer();

        if (currentQuestionAnswer == userGuess){

            Toast.makeText(getApplicationContext(), R.string.correct_toeast_message, Toast.LENGTH_LONG).show();

            mUserScore = mUserScore + 1;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast_message, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "onstart  metod", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "Onresumed metod", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "Onpause metod", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(), "Onstop metod", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "Ondestroy metod", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionInex);
    }
}
