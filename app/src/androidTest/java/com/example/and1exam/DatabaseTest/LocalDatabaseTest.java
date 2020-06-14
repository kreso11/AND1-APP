package com.example.and1exam.DatabaseTest;


import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.and1exam.Retrofit_Networking.GETResponse.QuestionResponse;
import com.example.and1exam.Retrofit_Networking.GETResponse.Results;
import com.example.and1exam.room_Database.Entity.Question;
import com.example.and1exam.room_Database.LocalDatabase;
import com.example.and1exam.room_Database.databaseDao;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.ArrayList;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class LocalDatabaseTest {

        private databaseDao databaseDao;
        private LocalDatabase db;

        private Question question;


        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, LocalDatabase.class).build();
            databaseDao = db.databaseDao();

            ArrayList<String> incans = new ArrayList<String>();
            incans.add("Incorrect answer");

            question = new Question(new QuestionResponse(0,new Results("History","multiple","hard","In the year 1900, what were the most popular first names given to boy and girl babies born in the United States?","John and Mary",incans)));
        }

        @After
        public void closeDb() throws IOException {
            db.close();
        }

        @Test
        public void writeAndReadQuestion() throws Exception{
            databaseDao.insertQuestion(question);
            assertThat(databaseDao.getRandomQuestionAndAnswer().getCategory(),equalTo("History"));
        }


        @Test
        public void writeAndReadIncorrectAnswers() throws Exception{
            databaseDao.insertQuestion(question);
            Gson gson = new Gson();
            String arrayOfIncInJson = databaseDao.getRandomQuestionAndAnswer().getIncorrect_answers();
            assertThat(gson.fromJson(arrayOfIncInJson,ArrayList.class).get(0),equalTo("Incorrect answer"));
        }

        @Test
        public void deleteAll() throws Exception{
            databaseDao.insertQuestion(question);
            databaseDao.deleteAllQuestionsStored();
            assertThat(databaseDao.getRandomQuestionAndAnswer(),equalTo(null));
        }

        @Test
        public void writeAndReadByCategory() throws Exception {
            databaseDao.insertQuestion(question);
            assertThat(databaseDao.getQuestionListFromDatabase("History").get(0).getCategory(),equalTo("History"));
        }
}

