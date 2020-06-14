package com.example.and1exam.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.and1exam.R;
import com.example.and1exam.room_Database.Entity.Question;
import com.google.gson.Gson;

import org.unbescape.html.HtmlEscape;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private Button getQuestionButton,answer1,answer2,answer3,answer4,googleButton;
    private TextView question;

    ArrayList<Button> buttons = new ArrayList<>();

    String answer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        getQuestionButton=root.findViewById(R.id.home_get_question_button);
        getQuestionButton.setOnClickListener(this);

        answer1=root.findViewById(R.id.home_answer_1);
        answer1.setOnClickListener(this);

        answer2=root.findViewById(R.id.home_answer_2);
        answer2.setOnClickListener(this);

        answer3=root.findViewById(R.id.home_answer_3);
        answer3.setOnClickListener(this);

        answer4=root.findViewById(R.id.home_answer_4);
        answer4.setOnClickListener(this);

        googleButton=root.findViewById(R.id.google_button);
        googleButton.setOnClickListener(this);

        //Text Views
        question = root.findViewById(R.id.text_home);


        //Setting question on startup
        answer1.setBackgroundColor(Color.parseColor("#03DAC5"));
        answer2.setBackgroundColor(Color.parseColor("#03DAC5"));
        answer3.setBackgroundColor(Color.parseColor("#03DAC5"));
        answer4.setBackgroundColor(Color.parseColor("#03DAC5"));

        homeViewModel.getQuestion();

        //FOR RANDOM IMPL
        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.home_get_question_button):
                answer1.setBackgroundColor(Color.parseColor("#03DAC5"));
                answer2.setBackgroundColor(Color.parseColor("#03DAC5"));
                answer3.setBackgroundColor(Color.parseColor("#03DAC5"));
                answer4.setBackgroundColor(Color.parseColor("#03DAC5"));

                answer1.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                answer4.setVisibility(View.VISIBLE);
                googleButton.setVisibility(View.VISIBLE);

                Toast.makeText(getContext(), "Getting Question", Toast.LENGTH_SHORT).show();
                Question questionGot = homeViewModel.getQuestionAndAnswer();
                String questionQuestion = HtmlEscape.unescapeHtml(questionGot.getQuestion());
                question.setText(questionQuestion);

                Gson gson = new Gson();
                ArrayList answers = gson.fromJson(questionGot.getIncorrect_answers(), ArrayList.class);

                Log.i("IN FRAGMENT", questionGot.getCorrect_answer());

                this.answer = questionGot.getCorrect_answer();

                Collections.shuffle(buttons);
                buttons.get(0).setText((String) answers.get(0));
                buttons.get(1).setText((String) answers.get(1));
                buttons.get(2).setText((String) answers.get(2));
                buttons.get(3).setText(questionGot.getCorrect_answer());

                break;
            case R.id.google_button:
                String query=null;
                try {
                    query = URLEncoder.encode(question.getText().toString(), "UTF-8");
                    Uri uri = Uri.parse("http://www.google.com/#q=" + query);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
        }
        if (v.getId() == R.id.home_answer_1) {
            if (checkIfCorrect(answer1.getText().toString())) {
                colorCorrectAnswer(1);
            } else {
                answer1.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        } else if (v.getId() == R.id.home_answer_2) {
            if (checkIfCorrect(answer2.getText().toString())) {
                colorCorrectAnswer(2);
            } else {
                answer2.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        } else if (v.getId() == R.id.home_answer_3) {
            if (checkIfCorrect(answer3.getText().toString())) {
                colorCorrectAnswer(3);
            } else {
                answer3.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        } else if (v.getId() == R.id.home_answer_4) {
            if (checkIfCorrect(answer4.getText().toString())) {
                colorCorrectAnswer(4);
            } else {
                answer4.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
    }

    public boolean checkIfCorrect(String answer){
        return answer.equals(this.answer);
    }

    public void colorCorrectAnswer(int i){
        if (i == 1) {
            answer1.setBackgroundColor(Color.parseColor("#008000"));
            answer2.setBackgroundColor(Color.parseColor("#FF0000"));
            answer3.setBackgroundColor(Color.parseColor("#FF0000"));
            answer4.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        if (i == 2) {
            answer1.setBackgroundColor(Color.parseColor("#FF0000"));
            answer2.setBackgroundColor(Color.parseColor("#008000"));
            answer3.setBackgroundColor(Color.parseColor("#FF0000"));
            answer4.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        if (i == 3) {
            answer1.setBackgroundColor(Color.parseColor("#FF0000"));
            answer2.setBackgroundColor(Color.parseColor("#FF0000"));
            answer3.setBackgroundColor(Color.parseColor("#008000"));
            answer4.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        if (i == 4) {
            answer1.setBackgroundColor(Color.parseColor("#FF0000"));
            answer2.setBackgroundColor(Color.parseColor("#FF0000"));
            answer3.setBackgroundColor(Color.parseColor("#FF0000"));
            answer4.setBackgroundColor(Color.parseColor("#008000"));
        }
    }
}
