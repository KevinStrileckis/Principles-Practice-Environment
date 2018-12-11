/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

/**
 *
 * @author Strilekn
 */
public class Answer {
    private String text;
    private boolean correct;
    
    public Answer(String t, boolean c){
        text = t;
        correct = c;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the isCorrect
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setCorrect(boolean isCorrect) {
        this.correct = isCorrect;
    }
}
