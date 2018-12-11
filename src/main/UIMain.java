/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import question.Question;
import question.QuestionPuller;
import question.Score;
import reporter.Reporter;

/**
 *
 * @author Strilekn
 */
public class UIMain implements ActionListener{

    private final JFrame top;
    private final JPanel stats;
    private JLabel statsLabel;
    private final JPanel question;
    private final JTextArea questionArea;
    private final JPanel answers;
    private final JPanel submission;
    private final JButton submitButton;
    private ArrayList<JCheckBox> answersButtons;
    
    //Layout
    private final GridBagLayout layout;
    private final GridBagConstraints cons;
    
    //Question Puller
    private final File currentTest;
    private QuestionPuller qp;
    private int questionIndex;
    private Score[] score;
    
    //ID
    private String name;
    
    public UIMain(File test, String n) throws FileNotFoundException{
        
        //Set ID
        name = n;
        
        //Initialize frame, textarea, and panels
        top = new JFrame("Strileckis Principles Practice Environment");
        stats = new JPanel();
        question = new JPanel();
        questionArea = new JTextArea();
        submission = new JPanel();
        answers = new JPanel();
        
        //Get the Questions
        currentTest = test;
        qp = new QuestionPuller(currentTest);
        //Scramble questions
        qp.scrambleBankAndAnswers();
        
        //Layout
        layout = new GridBagLayout();
        cons = new GridBagConstraints();
        top.setLocation(270, 100);
        top.setLayout(layout);
        answers.setLayout(layout);
        top.setPreferredSize(new Dimension(600, 500));
        
        statsLabel = new JLabel();
        stats.add(statsLabel);
        //Update stats
        updateStats();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 3;
        cons.gridheight = 1;
        top.add(stats, cons);
        
        question.setPreferredSize(new Dimension(401, 126));
        questionArea.setPreferredSize(new Dimension(400,125));
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        question.add(questionArea);
        cons.gridy = 1;
        cons.gridwidth = 3;
        top.add(question, cons);
        
        cons.gridy = 2;
        top.add(answers, cons);
        
        cons.gridy = 3;
        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(this);
        submission.add(submitButton);
        top.add(submission, cons);
        
        //Set integers
        score = new Score[qp.getBank().size()];
        for(int i=0; i<score.length; ++i)
            score[i] = new Score(qp.getBank().get(i).getQuestion());
        questionIndex = 0;
        
        
        //
        top.pack();
        top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        top.setVisible(true);
        showNextQuestion();
    }
    
    private void makeButtonsForQuestion(Question q){
        answersButtons = new ArrayList<JCheckBox>();
        cons.gridx = 0;
        cons.gridwidth = 1;
        cons.ipady = 10;
        
        for(int i=0, len = q.getAnswers().size(); i<len; ++i){
            //Add a new button
            answersButtons.add(new JCheckBox(q.getAnswers().get(i).getText()));
            //Add this as listener
            answersButtons.get(i).addActionListener(this);
            //Widen the button
            answersButtons.get(i).setPreferredSize(new Dimension(401, 
                    300 / len));
            
            cons.gridy = i;
            answers.add(answersButtons.get(i), cons);
        }
        
        answers.revalidate();
        answers.repaint();
    }
    
    private void showNextQuestion(){
        
        
        //Change question text
            //Make sure to go onto a new line each time we find a newline character (we'll just use '\', but assume it is followed with 'n' )
        String newText = qp.getBank().get(questionIndex).getQuestion();
        for(int i=0; i<newText.length(); ++i){
            if(newText.charAt(i) == '\\' )
                newText = newText.substring(0,i) + "\n" + newText.substring(i+2);
        }
        questionArea.setText(newText);
        
        //Clean out answers
        if(answersButtons != null){
            for(JCheckBox j : answersButtons){
                answers.remove(j);
            }
            
            answers.revalidate();
            answers.repaint();
            answersButtons.clear();
        }
        
        //Get new answers
        makeButtonsForQuestion(qp.getBank().get(questionIndex));
    }
    
    
    //Uses question bank and question index to determine if the proper boxes have been checked
    private boolean checkAllCheckBoxes(){
        for(int i=0, len=answersButtons.size(); i < len; ++i){
            if( !(answersButtons.get(i).isSelected() && qp.getBank().get(questionIndex).getAnswers().get(i).isCorrect())
                && !(!answersButtons.get(i).isSelected() && !qp.getBank().get(questionIndex).getAnswers().get(i).isCorrect() )   )
            {
                return false;
            }
        }
        return true;
    }
    
    //Handle end
    private boolean handleIfAtEnd(){
        //If we are not at the end, return
        if(questionIndex < qp.getBank().size()-1)
            return false;
        
        try {
            //Write out to file
            Reporter.exportUniqueFile(name, score);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UIMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UIMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Exit program
        top.dispose();
        System.exit(0);//Well, at least it works...
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the index of the answer -- unnecessary
        //int index = answersButtons.indexOf(e.getSource());
        
        if(e.getActionCommand().equals(submitButton.getActionCommand()))
        {
            System.out.println("Submit button pressed");
            if(checkAllCheckBoxes()){
                if(handleIfAtEnd())
                    return;
                
                score[questionIndex].addScore(1);
                questionIndex++;
                showNextQuestion();
                updateStats();
            }
            else{
                score[questionIndex].addScore(-1);
                JOptionPane.showConfirmDialog(top, 
                        "Your score has been subtracted by one", 
                        "Incorrect answer", 
                        JOptionPane.OK_OPTION);
                updateStats();
            }
        }
    }
    
    private void updateStats(){
        statsLabel.setText(sumUpScores() + " points total. On problem " + (1+questionIndex) + " / " + qp.getBank().size());
        stats.revalidate();
        stats.repaint();
    }
    
    private int sumUpScores(){
        int total = 0;
        if(score == null)
            return total;
        for(int i=0; i < score.length; ++i)
            total += score[i].getScore();
        
        return total;
    }
    
}
