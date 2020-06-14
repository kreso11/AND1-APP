package com.example.and1exam.Retrofit_Networking.GETResponse;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.xml.transform.Result;

public class QuestionResponse {
    private int response_code;
    private ArrayList<Results> results;


    public QuestionResponse(int response_code,Results results){
        this.results = new ArrayList<>();

        this.response_code=response_code;
        this.results.add(results);
    }

    public QuestionResponse(int response_code, String results) {
        Gson gson = new Gson();

        this.results = new ArrayList<>();

        this.response_code = response_code;

        this.results = gson.fromJson((results), (Type) Results.class);
    }

    public String getQuestion(){
        return results.get(0).getQuestion();
    }

    public String getType(){
        return results.get(0).getType();
    }

    public String getDifficulty(){
        return results.get(0).getDifficulty();
    }

    public String getCategory(){
        return results.get(0).getCategory();
    }

    public String cor_answer(){
        return results.get(0).getCorrect_answer();
    }

    public ArrayList<String> inc_answer(){
        return results.get(0).getIncorrect_answers();
    }
}
