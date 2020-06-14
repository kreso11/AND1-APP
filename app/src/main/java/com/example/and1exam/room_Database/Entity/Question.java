package com.example.and1exam.room_Database.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.and1exam.Retrofit_Networking.GETResponse.QuestionResponse;
import com.google.gson.Gson;

@Entity(tableName = "Questions_table")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int questionID;
    private String category;
    private String question;
    private String type;
    private String difficulty;
    private String correct_answer;
    private String incorrect_answers;

    public Question(String category, String question, String type, String difficulty, String correct_answer, String incorrect_answers) {
        this.category = category;
        this.question = question;
        this.type = type;
        this.difficulty = difficulty;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    public Question(QuestionResponse questionResponse) {
        Gson gson = new Gson();

        this.category = questionResponse.getCategory();
        this.question = questionResponse.getQuestion();
        this.type = questionResponse.getType();
        this.difficulty = questionResponse.getDifficulty();
        this.correct_answer = questionResponse.cor_answer();
        this.incorrect_answers = gson.toJson(questionResponse.inc_answer());
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getIncorrect_answers() {
        return incorrect_answers;
    }
}
