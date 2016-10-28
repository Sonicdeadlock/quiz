package io.sonicdeadlock.quiz.util;

import io.sonicdeadlock.quiz.Quiz;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 9/25/2016.
 */
public class QuizLoader {
    public static List<Quiz> loadQuizzes(String location){
        StringBuilder source = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(location));
            String line;
            while ((line=br.readLine())!=null){
                source.append(line);
            }
        } catch (FileNotFoundException e) {
           return new ArrayList<Quiz>();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonQuizzes = new JSONArray(source.toString());
        List<Quiz> quizzes = new ArrayList<Quiz>();
        for (int i = 0; i < jsonQuizzes.length(); i++) {
            quizzes.add(Quiz.fromJSONObject(jsonQuizzes.getJSONObject(i)));
        }
        return quizzes;
    }
}
