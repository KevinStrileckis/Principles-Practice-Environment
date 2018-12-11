/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

/**
 *
 * @author Kevin Strileckis
 */
public class Score {
    private int score;
    private String question;
    
    public Score(){
        score = 0;
        question = "";
    }
    
    public Score(String q){
        score = 0;
        question = q;
    }
    
    public void addScore(int x){
        score += x;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }
}
