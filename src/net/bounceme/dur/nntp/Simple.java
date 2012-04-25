/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bounceme.dur.nntp;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thufir
 */
public class Simple {

    private static JFrame f = new JFrame();
    private static JTextPane text = new JTextPane();
    private static JSlider slider = new JSlider();
    private static DefaultTableModel dtm = MessageUtils.getDataTableModel();
    private static JTable table = new JTable();

    public static void main(String args[]) {
        table.setModel(dtm);
        slider.setMaximum(MessageUtils.getMax());
        slider.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }

            private void jSlider1StateChanged(ChangeEvent evt) {
                int index = slider.getValue();
                MessageUtils.setIndex(index);
                dtm = MessageUtils.getDataTableModel();
            }
        });

        f.add(table);
        f.add(text);
        f.add(slider);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(screenSize);
        f.setVisible(true);
    }
}
