/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.util.ArrayList;

/**
 *
 * @author Strilekn
 */
public class Question {
    private String question;
    private ArrayList<Answer> answers;
    
    public Question(String q, ArrayList<String> a, ArrayList<Boolean> b){
        question = q;
        answers = new ArrayList<Answer>();
        for(int i=0, len = a.size(); i<len; ++i){
            answers.add(new Answer(a.get(i), b.get(i)));
        }
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

    /**
     * @return the answers
     */
    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    void scrambleAnswers() {
        ArrayList<Answer> newAnswers = new ArrayList<>();
        
        for(int i=0, len=answers.size(), rand; i < len; ++i){
            //Make sure that the random number is adjusted for the new length
            rand = (int)(Math.random()*(len-i));
            
            //Remove the one at rand while adding to the new bank
            newAnswers.add(answers.remove(rand));
        }
        
        //Add all back to original answer group
        for(Answer a : newAnswers){
            answers.add(a);
        }
    }
    
}


//Which BEST describes the term metadata?

//Data about other data
//Information used in operations and calculations
//After or beyond. Often used to describe abstraction



//Meta data is which of the following?

//The author and title of a book
//The date something was last modified
//The actual data stored in a file