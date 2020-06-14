package com.example.and1exam.Retrofit_Networking;

import com.example.and1exam.Retrofit_Networking.GETResponse.QuestionResponse;
import com.example.and1exam.Retrofit_Networking.GETResponse.Results;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionAPI {

    @GET("api.php?amount=1&type=multiple")
    Call<QuestionResponse> getARandomQuestion();

}
