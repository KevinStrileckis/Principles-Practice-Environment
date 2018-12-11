/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Strilekn
 */
public class CanvasPanel extends JPanel {
    private final int width, height;
    
    private boolean isBlank;
    
    public CanvasPanel(int w, int h){
        width = w;
        height = h;
        isBlank = true;
        
    }
    
    //Drawing
    
    public void drawBlank(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        g2d.setColor(Color.white);
        int temp = 4;
        g2d.drawString("No graphic for\n this question", width/temp, height/temp);
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        if(isBlank){
            drawBlank(g);
            return;
        }        
    }
    
    public void paintComponent(Graphics g, BufferedImage b, Dimension d, int x, int y) {
        
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
