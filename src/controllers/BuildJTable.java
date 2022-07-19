package controllers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class BuildJTable  {
    public BuildJTable(Object[][] data) throws IOException {
        //setup header and table fonts
        Font HeaderFont = new Font("sansserif", Font.BOLD,30);
        Font bigFont = new Font("sansserif", Font.BOLD,24);
        // this sets up how many columns are needed
        String[] columns = {"Callsign", " ", " ", " ", " ", " ", " ","","","","","","","","","","","","","","","","","","","","","","","","","","",};
        //create table
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model){
            public Class getColumnClass ( int column){
                return (column >= 2) ? Icon.class : Object.class;
            }
        };
        //this centers table text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        //set table parameters
        table.setRowHeight(48);
        table.getColumnModel().getColumn(0).setMinWidth(300);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.setFont(bigFont);
        table.getTableHeader().setFont(HeaderFont);
        table.getTableHeader().setBackground(Color.WHITE);
        //add scroll pane to make table visible
        JScrollPane scrollPane = new JScrollPane(table);
        //make jframe to display table
        JFrame frame = new JFrame();
        //jfram parameters
        frame.getContentPane().add(scrollPane);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(1700,650);
        frame.setTitle("hello");
        frame.setVisible(true);
        //create new image
        BufferedImage image = new BufferedImage(1700,604,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        frame.paint(g);
        frame.dispose();
        //save new image
        ImageIO.write(image,"PNG", new File("C:/HypeMan/img/greenie/chart.png"));
    }
}

