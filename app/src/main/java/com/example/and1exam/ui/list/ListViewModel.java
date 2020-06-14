package com.example.and1exam.ui.list;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.and1exam.room_Database.Entity.Question;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private ListRepository repository;

    public ListViewModel(Application application) {
        super(application);
        repository = ListRepository.getInstance(application);
    }

    public List<Question> getListOfSpecificCategory(String category){
        return repository.getQuestionsWhereCategory(category);
    }
}