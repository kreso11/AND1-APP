package com.example.and1exam.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.and1exam.room_Database.Entity.Question;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    private HomeRepository repository;

    public HomeViewModel(Application application) {
        super(application);
        repository=HomeRepository.getInstance(application);
    }

    public void getQuestion(){
         repository.getNewRandomQuestion();
    }

    public Question getQuestionAndAnswer(){
        return repository.getRandomQuestionFromDatabase();
    }
}