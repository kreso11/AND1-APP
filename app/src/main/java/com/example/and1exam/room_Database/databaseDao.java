package com.example.and1exam.room_Database;

        import com.example.and1exam.room_Database.Entity.Question;

        import androidx.room.Dao;
        import androidx.room.Database;
        import androidx.room.Insert;
        import androidx.room.Query;

        import java.util.List;


@Dao
public interface databaseDao {

    @Insert
    void insertQuestion(Question question);

    @Query("DELETE FROM Questions_table")
    void deleteAllQuestionsStored();

    @Query("SELECT * FROM Questions_table ORDER BY RANDOM() LIMIT 1")
    Question getRandomQuestionAndAnswer();

    @Query("SELECT * FROM Questions_table WHERE category = :category")
    List<Question> getQuestionListFromDatabase(String category);
}
