/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Strilekn
 */
public class QuestionPuller {
    private ArrayList<Question> bank;
    
    public QuestionPuller(File source) throws FileNotFoundException{
        FileReader fr = new FileReader(source);
        BufferedReader br = new BufferedReader(fr);
        String buffer, questionHolder;
        ArrayList<String> answersHolder = new ArrayList<>();
        ArrayList<Boolean> booleansHolder = new ArrayList<>();
        int temp;
        
        bank = new ArrayList<Question>();
        try{
            while((buffer = br.readLine()) != null){
                //Get number of answers
                temp = Integer.valueOf(buffer.charAt(0)) - 48;
                
                //Save the question
                questionHolder = buffer.substring(1);
                System.out.println(temp + " " +questionHolder);
                //Get the answers
                for(int i=0; i<temp; ++i)
                {
                    answersHolder.add(br.readLine());
                    System.out.println(i);
                    //Get next  line
                    if(br.readLine().equals("T")){
                        booleansHolder.add(true);
                    }
                    else
                        booleansHolder.add(false);
                }
                
                //Add q&a to question bank
                bank.add(new Question(questionHolder, answersHolder, booleansHolder));
                //Clear out our ArrayLists
                answersHolder.clear();
                booleansHolder.clear();
            }
        }
        catch(IOException e)
        {
            
        }
    }
    
    public void scrambleBank(){
        ArrayList<Question> newBank = new ArrayList<>();
        
        for(int i=0, len=bank.size(), rand; i < len; ++i){
            //Make sure that the random number is adjusted for the new length
            rand = (int)(Math.random()*(len-i));
            
            //Remove the one at rand while adding to the new bank
            newBank.add(bank.remove(rand));
        }
        
        //Add all back into question bank
        for(Question q : newBank)
            bank.add(q);
    }
    
    public void scrambleBankAndAnswers(){
        ArrayList<Question> newBank = new ArrayList<>();
        
        for(int i=0, len=bank.size(), rand; i < len; ++i){
            //Make sure that the random number is adjusted for the new length
            rand = (int)(Math.random()*(len-i));
            
            //Remove the one at rand while adding to the new bank
            newBank.add(bank.remove(rand));
        }
        
        //Add all back into question bank, while scrambling answers
        for(Question q : newBank){
            q.scrambleAnswers();
            bank.add(q);
        }
    }

    /**
     * @return the bank
     */
    public ArrayList<Question> getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(ArrayList<Question> bank) {
        this.bank = bank;
    }
}
