package com.example.and1exam.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and1exam.R;
import com.example.and1exam.room_Database.Entity.Question;

import org.unbescape.html.HtmlEscape;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    private ArrayList<Question> questions;

    public QuestionAdapter(ArrayList<Question> questions){
        this.questions=questions;

        for (int i = 0; i < questions.size(); i++) {
            this.questions.get(i).setQuestion(HtmlEscape.unescapeHtml(questions.get(i).getQuestion()));
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.question.setText(questions.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView question;

        ViewHolder(View itemView){
            super(itemView);
            question=itemView.findViewById(R.id.question);
        }
    }
}
