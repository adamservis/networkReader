/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;

import java.util.LinkedList;

import javax.swing.JComponent;

/**
 *
 * @author Adam
 */
public class Connection extends JComponent{
    //defines a line object used to draw network connections
    public static class Line{
    final int x1; 
    final int y1;
    final int x2;
    final int y2;   
    final Color color;

    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }               
}

public static LinkedList<Line> lines = new LinkedList<Line>();
//adds a line to the linked list
public void addLine(int x1, int x2, int x3, int x4) {
    addLine(x1, x2, x3, x4, Color.black);
}

public void addLine(int x1, int x2, int x3, int x4, Color color) {
    lines.add(new Line(x1,x2,x3,x4, color));     
}
//clears the line list
//called when icons are repositioned, and links need to be redefined
public void clearLines() {
    lines.clear();
}

}
