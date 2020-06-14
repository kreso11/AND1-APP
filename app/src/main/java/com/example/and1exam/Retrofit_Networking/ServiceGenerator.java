package com.example.and1exam.Retrofit_Networking;

import com.example.and1exam.room_Database.Entity.Question;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.and1exam.Retrofit_Networking.NetworkConfig.BASE_URL;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static QuestionAPI questionAPI = retrofit.create(QuestionAPI.class);

    public  static QuestionAPI questionAPI() {return questionAPI;}

}
