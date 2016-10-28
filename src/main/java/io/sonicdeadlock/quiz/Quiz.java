package io.sonicdeadlock.quiz;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alex on 9/25/2016.
 */
public class Quiz{
    private int currentQuestionIndex =-1;
    private List<Boolean> correctAnswers;
    private List<Question> questions = new ArrayList<Question>();
    private String name;
    private String alias="";
    private boolean isGetFirst =true;
    public Quiz(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public Quiz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public boolean hasNextQuestion(){
        return currentQuestionIndex+1 < this.questions.size();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getNextQuestion(){
        currentQuestionIndex++;
        Question q =getCurrentQuestion();
        return q;
    }

    private Question getCurrentQuestion(){
        return  this.questions.get(currentQuestionIndex);
    }

    public void reset(){
        correctAnswers = new ArrayList<Boolean>();
        for (int i = 0; i < this.questions.size(); i++) {
            correctAnswers.add(false);
        }
        isGetFirst = true;
        currentQuestionIndex =-1;
    }

    /**
     *
     * @param answer The answer to the question
     * @return returns if the answer to the question was correct
     */
    public boolean answerCurrentQuestion(String answer){
        Question currentQuestion = getCurrentQuestion();
        if(answer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())){
           markCorrectAnswer(answer);
            return true;
        }else {
            try{
                int answerIndex = Integer.parseInt(answer)-1;//minus one because the answers start at one
                String shuffledAnswer  = currentQuestion.getShuffledAnswers().get(answerIndex);
                if(shuffledAnswer.equals(currentQuestion.getCorrectAnswer())){
                    markCorrectAnswer(shuffledAnswer);
                    return true;
                }else{
                    markIncorrectAnswer(shuffledAnswer);
                    return false;
                }
            }catch (Exception e){
                markIncorrectAnswer("Invalid answer index");
                return false;
            }
        }

    }

    private void markIncorrectAnswer(String answer){
        correctAnswers.set(currentQuestionIndex,false);
        getCurrentQuestion().incrementAnswerFrequency(answer);
    }

    private void markCorrectAnswer(String answer){
        correctAnswers.set(currentQuestionIndex,true);
        getCurrentQuestion().incrementAnswerFrequency(answer);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getScore(){
        int correctCount=0;
        for (Boolean correctAnswer : correctAnswers) {
            if(correctAnswer)
                correctCount++;
        }
        return correctCount*100.0/correctAnswers.size();
    }

    public JSONObject toJSONObject(){
        JSONObject object = new JSONObject();
        JSONArray questions = new JSONArray();
        for (Question question :
                this.questions) {
            questions.put(question.toJSONObject());
        }
        object.put("questions",questions);
        object.put("name",this.name);
        object.put("alias",this.alias);
        return object;
    }

    public static Quiz fromJSONObject(JSONObject object){
        String name = object.getString("name");
        String alias = object.getString("alias");
        Quiz quiz = new Quiz(name,alias);
        JSONArray questions = object.getJSONArray("questions");
        for (int i = 0; i < questions.length(); i++) {
            quiz.questions.add(Question.fromJSONObject(questions.getJSONObject(i)));
        }
        return quiz;
    }



}
