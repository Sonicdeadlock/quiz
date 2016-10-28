package io.sonicdeadlock.quiz;

import org.json.JSONObject;

import java.util.*;

/**
 * Created by Alex on 9/25/2016.
 */
public class Question {
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;
    private Map<String,Integer> answerFrequency;
    private List<String> shuffledAnswers;

    public Question(String question, String correctAnswer, List<String> incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        answerFrequency = new HashMap<String, Integer>();
    }

    private Question(String question, String correctAnswer, List<String> incorrectAnswers, Map<String, Integer> answerFrequency) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.answerFrequency = answerFrequency;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public int getAnswerFrequency(String answer){
        return answerFrequency.get(answer);
    }

    public void incrementAnswerFrequency(String answer){
        if(answerFrequency.containsKey(answer)){
            answerFrequency.put(answer,answerFrequency.get(answer)+1);
        }else{
            answerFrequency.put(answer,1);
        }
    }

    public List<String> getShuffledAnswers(){
        if(this.shuffledAnswers==null){
            this.shuffledAnswers = new ArrayList<String>();
            this.shuffledAnswers.addAll(incorrectAnswers);
            this.shuffledAnswers.add(correctAnswer);
            Collections.shuffle(this.shuffledAnswers);
        }

        return this.shuffledAnswers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public JSONObject toJSONObject(){
        JSONObject obj = new JSONObject();
        obj.put("question",question);
        obj.put("correctAnswer", correctAnswer);
        obj.put("incorrectAnswers",incorrectAnswers);
        obj.put("answerFrequency",answerFrequency);
        return obj;
    }

    public static Question fromJSONObject(JSONObject object){
        String question = object.getString("question");
        String correctAnswer = object.getString("correctAnswer");
        List<String> incorrectAnswers = new ArrayList<String>();
        for (Object next : object.getJSONArray("incorrectAnswers")) {
            if (next instanceof String)
                incorrectAnswers.add((String) next);
        }
        Map<String,Integer> answerFrequency = new HashMap<String, Integer>();
        JSONObject jsonAnswerFrequency = object.getJSONObject("answerFrequency");
        for(String key : jsonAnswerFrequency.keySet()){
            answerFrequency.put(key,jsonAnswerFrequency.getInt(key));
        }
        return new Question(question,correctAnswer,incorrectAnswers,answerFrequency);
    }
}
