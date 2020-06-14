package com.example.and1exam.ui.home;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.and1exam.Retrofit_Networking.GETResponse.QuestionResponse;
import com.example.and1exam.Retrofit_Networking.QuestionAPI;
import com.example.and1exam.Retrofit_Networking.ServiceGenerator;
import com.example.and1exam.room_Database.Entity.Question;
import com.example.and1exam.room_Database.LocalDatabase;
import com.example.and1exam.room_Database.databaseDao;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private databaseDao databaseDao;

    private static HomeRepository instance;

    private Question question;

    private HomeRepository(Application application)
    {
        LocalDatabase database = LocalDatabase.getInstance(application);
        databaseDao = database.databaseDao();
    }


    public static synchronized HomeRepository getInstance(Application application) {
        if (instance == null) {
            instance = new HomeRepository(application);
        }
        return instance;
    }

    public void getNewRandomQuestion(){
        QuestionAPI questionAPI = ServiceGenerator.questionAPI();
        Call<QuestionResponse> call =questionAPI.getARandomQuestion();

        call.enqueue(new Callback<QuestionResponse>()
        {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.code() == 200) {

                    QuestionResponse question = response.body();

                    Log.i("Got question, inserting into Database",question.getQuestion());

                    new InsertQuestion(databaseDao).execute(new Question(question));

                }
                else {
                    Log.i("Failed to get question",""+call.request().url().toString());
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                Log.i("Failed to get question", "Call failed");
            }
        });
    }

    public Question getRandomQuestionFromDatabase(){
        try {
            return new getRandomQuestion(databaseDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("RETURN","NULL");
        return null;
    }


    private static class getRandomQuestion extends AsyncTask<Void,Void,Question>
    {
        private databaseDao databaseDao;

        private getRandomQuestion(databaseDao databaseDao){
            this.databaseDao=databaseDao;
        }

        @Override
        protected Question doInBackground(Void... voids) {
            Question q= databaseDao.getRandomQuestionAndAnswer();
            Log.i("RETURN",q.toString());
            return q;
        }
    }

    private static class InsertQuestion extends AsyncTask<Question,Void,Void>
    {
        private databaseDao databaseDao;

        private InsertQuestion(databaseDao databaseDao){
            this.databaseDao=databaseDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            databaseDao.insertQuestion(questions[0]);
            return null;
        }
    }
}
