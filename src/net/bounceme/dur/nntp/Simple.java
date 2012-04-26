package net.bounceme.dur.nntp;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class Simple {

    private static JFrame f = new JFrame();
    private static JSlider slider = new JSlider();
    private static DefaultTableModel dtm = new DefaultTableModel();

    public static void main(String args[]) {

        MessageUtils.init();
        slider.setMaximum(MessageUtils.getMax());
        //slider.setValue(MessageUtils.getMax());
        slider.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int index = slider.getValue();
                MessageUtils.setIndex(index);
                //dtm = MessageUtils.getDataTableModel();
                f.revalidate();
                f.repaint();
            }
        });
        f.add(slider);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(screenSize);
        f.setVisible(true);
    }
}
