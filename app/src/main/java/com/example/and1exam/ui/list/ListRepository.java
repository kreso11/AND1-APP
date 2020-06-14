package com.example.and1exam.ui.list;

import android.app.Application;
import android.os.AsyncTask;

import com.example.and1exam.room_Database.Entity.Question;
import com.example.and1exam.room_Database.LocalDatabase;
import com.example.and1exam.room_Database.databaseDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListRepository {

    private databaseDao databaseDao;

    private static ListRepository instance;


    private ListRepository(Application application)
    {
        LocalDatabase database = LocalDatabase.getInstance(application);
        databaseDao = database.databaseDao();
    }


    public static synchronized ListRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ListRepository(application);
        }
        return instance;
    }

    public List<Question> getQuestionsWhereCategory(String category){
        try {
            return new getQuestionListByCategory(databaseDao).execute(category).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static class getQuestionListByCategory extends AsyncTask<String,Void,List<Question>>
    {
        private databaseDao databaseDao;

        private getQuestionListByCategory(databaseDao databaseDao){
            this.databaseDao=databaseDao;
        }

        @Override
        protected List<Question> doInBackground(String... strings) {
            List<Question> q= databaseDao.getQuestionListFromDatabase(strings[0]);
            return q;
        }
    }
}
