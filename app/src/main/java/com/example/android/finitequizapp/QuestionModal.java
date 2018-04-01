package com.example.android.finitequizapp;

/**
 * Created by GauravSha on 25-03-2018.
 */

public class QuestionModal {

    private String QuestionString;
    private String Answer;

    public QuestionModal(String questionString, String answer) {
        QuestionString = questionString;
        Answer = answer;
    }

    public String getQuestionString() {
        return QuestionString;
    }

    public void setQuestionString(String questionString) {
        QuestionString = questionString;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
