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

    public static void main(String args[]) {
        table.setModel(dtm);

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                MessageBean mb = MessageUtils.getMessageBean(row);
                text.setText(mb.getContent());
                text.setContentType("text/html");
            }
        });

        slider.setMaximum(MessageUtils.getMax());
        slider.setValue(MessageUtils.getMax());
        slider.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int index = slider.getValue();
                MessageUtils.setIndex(index);
                dtm = MessageUtils.getDataTableModel();
                table.setModel(dtm);
                table.revalidate();
                table.repaint();
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
