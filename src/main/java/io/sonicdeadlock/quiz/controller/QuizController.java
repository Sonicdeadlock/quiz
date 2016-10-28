package io.sonicdeadlock.quiz.controller;

import io.sonicdeadlock.quiz.Quiz;
import io.sonicdeadlock.quiz.util.QuizLoader;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex on 9/28/2016.
 */
public class QuizController {
    private static QuizController ourInstance = new QuizController();
    private List<Quiz> quizzes;
    private String saveLocation;
    public static QuizController getInstance() {
        return ourInstance;
    }

    private QuizController() {
    }

    public String getSaveLocation() {
        return saveLocation;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    public List<Quiz> getQuizzes() {
        if(quizzes ==null)
            quizzes = QuizLoader.loadQuizzes(saveLocation);
        return quizzes;
    }

    public void save(){
        JSONArray jsonArray = new JSONArray();
        for (Quiz quiz :
                this.quizzes) {
            jsonArray.put(quiz.toJSONObject());
        }
        try {
            jsonArray.write(new FileWriter(saveLocation)).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
