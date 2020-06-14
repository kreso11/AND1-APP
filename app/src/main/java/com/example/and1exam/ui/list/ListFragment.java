package com.example.and1exam.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and1exam.R;
import com.example.and1exam.room_Database.Entity.Question;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ListViewModel listViewModel;

    private RecyclerView questionList;
    private QuestionAdapter questionAdapter;

    private Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listViewModel =
                ViewModelProviders.of(this).get(ListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        questionList = root.findViewById(R.id.rv);
        questionList.hasFixedSize();
        questionList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        Spinner spinner = (Spinner) root.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.category_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        List<Question> questions = listViewModel.getListOfSpecificCategory("General Knowledge");

        questionAdapter = new QuestionAdapter((ArrayList<Question>) questions);
        questionList.setAdapter(questionAdapter);

        return root;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        List<Question> questions = listViewModel.getListOfSpecificCategory((String) parent.getItemAtPosition(position));

        questionAdapter = new QuestionAdapter((ArrayList<Question>) questions);
        questionList.setAdapter(questionAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
