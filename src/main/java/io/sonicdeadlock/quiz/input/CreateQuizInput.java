package io.sonicdeadlock.quiz.input;

import io.sonicdeadlock.quiz.Question;
import io.sonicdeadlock.quiz.Quiz;
import io.sonicdeadlock.quiz.util.InputUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 9/28/2016.
 */
public class CreateQuizInput {
    public static Quiz getQuiz(){
        String name = getQuizName();
        Quiz quiz;
        if(InputUtil.queryUserForBoolean("Would you like to give the quiz an alias?"))
            quiz = new Quiz(name,getQuizAlias());
        else
            quiz=new Quiz(name);
        addQuestions(quiz);
        quiz.reset();
        return quiz;
    }

    private static String getQuizName(){
        String query = "Please enter the name of the quiz";
        return InputUtil.queryUserForString(query);
    }

    private static String getQuizAlias(){
        return InputUtil.queryUserForString("Please enter the alias for the quiz");
    }

    public static void addQuestions(Quiz quiz){
        int counter = 1+quiz.getQuestions().size();
        do{
            System.out.println("Please enter the information for question "+counter);
            quiz.getQuestions().add(getQuestion());
        }while (InputUtil.queryUserForBoolean("Would you like to enter another question?"));
    }

    private static Question getQuestion(){
        String q = InputUtil.queryUserForString("Please enter the question:");
        String correctAnswer = getCorrectAnswer();
        List<String> incorrectAnswers = getIncorrectAnswers();
        Question question = new Question(q,correctAnswer,incorrectAnswers);
        return question;
    }

    private static String getAnswer(){
        return "";
    }

    private static String getCorrectAnswer(){
        return InputUtil.queryUserForString("Please enter the correct answer for the question");
    }

    public static List<String> getIncorrectAnswers(){
        List<String> incorrectAnswers = new ArrayList<String>();
        do{
            incorrectAnswers.add(InputUtil.queryUserForString("Please enter an incorrect answer for the question"));
        }while (InputUtil.queryUserForBoolean("Would you like to add another incorrectAnswer?"));

        return incorrectAnswers;
    }

}
