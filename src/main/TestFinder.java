/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Strilekn
 */
public class TestFinder extends JFrame{
    
    private JFileChooser browse;
    private File test;
    
    public TestFinder(){
        browse = new JFileChooser();
        try {
            //Set the default location to the parent directory. If this fails somehow, it will be Documents.
            browse.setCurrentDirectory(new File(UIMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
            //Try to open the lessons folder. If this fails, then the selector will fall back on the parent directory
            browse.setCurrentDirectory(new File(UIMain.class.getProtectionDomain().getCodeSource().getLocation().toURI() + "\\Question Bank"));
        } catch (URISyntaxException ex) {
            Logger.getLogger(UIMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int temp = browse.showOpenDialog(this);
        do{
            //Get test file
            test = browse.getSelectedFile();
        }while(temp != JFileChooser.APPROVE_OPTION);
       
    }

    /**
     * @return the test
     */
    public File getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(File test) {
        this.test = test;
    }
}
