package com.example.and1exam;

import com.example.and1exam.Retrofit_Networking.GETResponse.QuestionResponse;
import com.example.and1exam.Retrofit_Networking.GETResponse.Results;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class QuestionResponseUnitTest {

    private QuestionResponse questionResponse;

    private List<String> resultList;

    @Before
    public void create(){

        ArrayList<String> incans = new ArrayList<String>();
        incans.add("Incorrect answer");

        this.resultList=incans;

        questionResponse = new QuestionResponse(0,new Results(
                "History",
                "multiple",
                "hard",
                "In the year 1900, what were the most popular first names given to boy and girl babies born in the United States?",
                "John and Mary",incans));
    }

    @Test
    public void getQuestion() {
        assertEquals(questionResponse.getQuestion(), "In the year 1900, what were the most popular first names given to boy and girl babies born in the United States?");
    }
    @Test
    public void getCategory(){
        assertEquals(questionResponse.getCategory(), "History");
    }
    @Test
    public void getType(){
        assertEquals(questionResponse.getType(), "multiple");
    }
    @Test
    public void getDiff(){
        assertEquals(questionResponse.getDifficulty(), "hard");
    }
    @Test
    public void getCorrectAnswer(){
        assertEquals(questionResponse.cor_answer(), "John and Mary");
    }
    @Test
    public void getIncAnswer(){
        assertEquals(questionResponse.inc_answer(), resultList);
    }
}