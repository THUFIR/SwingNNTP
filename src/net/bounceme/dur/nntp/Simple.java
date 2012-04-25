package net.bounceme.dur.nntp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

public class Simple {

    private static JFrame f = new JFrame();
    private static JTextPane text = new JTextPane();
    private static JSlider slider = new JSlider();
    private static DefaultTableModel dtm = MessageUtils.getDataTableModel();
    private static JTable table = new JTable();

    public static void main(String args[]) {
        table.setModel(dtm);

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            private void tableMouseClicked(MouseEvent evt) {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        });

        slider.setMaximum(MessageUtils.getMax());
        slider.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }

            private void jSlider1StateChanged(ChangeEvent evt) {
                int index = slider.getValue();
                MessageUtils.setIndex(index);
                dtm = MessageUtils.getDataTableModel();
                f.revalidate();
                f.repaint();
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
