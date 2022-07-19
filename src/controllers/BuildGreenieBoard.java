package controllers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BuildGreenieBoard {
    //images location
    static String BoardFolder = "C:/HypeMan/img/greenie/";

    //test data
    static String Green = "C:\\Users\\fpere\\IdeaProjects\\HypemanTest\\src\\images\\squareGreen.png";
    static Object[][] data1 = {
            {"Circuit","4.0", new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green)},
            {"Circuit","4.0", new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green)},
            {"Circuit","4.0", new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green), new ImageIcon(Green)},
            {},{},{},{},{},{},{},{}
    };
     static void BuildGreenieBoard() throws IOException {
         //build trap data table
         new BuildJTable(data1);
         //read in Board, squadron Icon, and trap data
         BufferedImage image = ImageIO.read(new File(BoardFolder,"gb_bg_cvw7.png"));
         BufferedImage overlay = ImageIO.read(new File(BoardFolder, "gb_em_86.png"));
         BufferedImage data = ImageIO.read(new File(BoardFolder, "images/chart.png"));
         //set output image size
         int w = Math.max(image.getWidth(), overlay.getWidth());
         int h = Math.max(image.getHeight(), overlay.getHeight());
         //create new image
         BufferedImage combined = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);System.out.println(w + " " + h);
        //layer Board Squadron and Trap data on top of each other
         Graphics g = combined.getGraphics();
         g.drawImage(image, 0 , 0, null);
         g.drawImage(data,130,210,w-240,h-320,null);
         g.drawImage(overlay, 0 ,0, null);
         g.dispose();
        //save new image
         ImageIO.write(combined, "PNG", new File(BoardFolder, "combined.png"));
    }

    public static void main(String[] args) throws IOException {
        BuildGreenieBoard();
    }
}
