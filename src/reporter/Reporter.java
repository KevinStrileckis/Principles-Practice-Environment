/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import question.Score;

/**
 *
 * @author Kevin Strileckis
 */
public class Reporter {
    
    //TODO reverse unique files so to check for honesty
    
    private static int totalAnswers;
    private static int totalTotal;
    
    public static void resetTotals()
    {
        totalAnswers = 0;
        totalTotal = 0;
    }
    
    public static void exportUniqueFile(String name, Score[] s) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter(name+System.currentTimeMillis()+".cspp", "UTF-8");
        int i = 0;
        //Make 12 garbage lines
        while(i < 12)
        {
            writer.println(makeTrash(12));//garbage line;
            i++;
        }
        //Make 1 legit line + some garbage
        writer.println((scramble(name) + makeTrash(3)));
        //Make 20 garbage lines
        i=0;
        while(i < 20)
        {
            writer.println(makeTrash(12));//garbage line;
            i++;
        }
        //ID section
        writer.println((name + makeTrash(1,5)));
        writer.println(makeTrash(4, 16));//garbage line;
        writer.println(scramble(name) + makeTrash(2));
        for(int j=0; j<s.length; ++j)
            writer.println(scramble(s[j].getScore()) + s[j].getQuestion());
        writer.println(makeTrash(3, 10));//garbage line;
        writer.println(totalTotal / 2);
        writer.println(totalAnswers);
        writer.println(System.currentTimeMillis());
        //Make 36 garbage lines
        i=0;
        while(i < 36)
        {
            writer.println(makeTrash(12));//garbage line;
            i++;
        }
        //Make 30 garbage lines
        i=0;
        while(i < 30)
        {
            writer.println(makeTrash(4, 14));//garbage line;
            i++;
        }
        //We have 99 lines not in the ID sectionalready comepleted
        writer.close();
    }
    
    public static int getSum(ArrayList<String> strs)
    {   
        for(String s: strs)
        {
            for(int i=s.length()-1; i>=0; --i)
            {
                totalAnswers += s.charAt(i);
            }
        }
        
        return totalAnswers;
    }
    
    public static int getSumTotalTotal(ArrayList<String> strs)
    {   
        for(String s: strs)
        {
            for(int i=s.length()-1; i>=0; --i)
            {
                totalTotal += s.charAt(i);
            }
        }
        
        return totalAnswers;
    }
    
    private static String makeTrash(int e){
        String s2 = "";
        for(int i=0; i<e; i++){
            s2 += (char)(Math.random()*26 + 'a');
        }
        return s2;
    }
    
    private static String makeTrash(int b, int e){
        String s2 = "";
        e = (int)(Math.random()*e);
        e = (int)(Math.random()*(e-b) + b);
        for(int i=e; i>=b; i--){
            s2 += (char)(Math.random()*26 + 'a');
        }
        return s2;
    }
    
    private static String scramble(String s){
        String s2 = "";
        for(int i=s.length()-1; i>=0; i--){
            s2 += (char)(s.charAt(i) + 3);
        }
        return s2;
    }
    
    private static String scramble(int s){
        String s2 = "";
        s2 += (char)(s+'a');
        return s2;
    }
}
