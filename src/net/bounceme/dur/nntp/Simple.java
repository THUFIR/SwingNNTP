package net.bounceme.dur.nntp;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Simple {

    static JFrame f = new JFrame();
    static JTextPane text = new JTextPane();
    static JSlider slider = new JSlider();
    static MessagesController mc = new MessagesController();
    static DefaultTableModel dtm = new DefaultTableModel();//mc.getDataTableModel();
    static JTable table = new JTable();

    private static void createAndShowGUI() {


        table.setModel(dtm);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                int row = table.getSelectedRow();
                MessageBean mb = mc.getMessageBean(row);
                text.setText(mb.getContent());
                text.setContentType("text/html");
                text.revalidate();
                text.repaint();
            }
        });

        slider.setMaximum(mc.getMax());
        slider.setValue(mc.getMax());
        slider.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int index = slider.getValue();
                mc.setIndex(index);
                dtm = mc.getDataTableModel();
                table.setModel(dtm);
            }
        });

        table.getSelectionModel().setSelectionInterval(1, 1);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(table);
        panel.add(text);
        panel.add(slider);
        f.add(panel);
        table.revalidate();
        table.repaint();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.pack();
        f.setVisible(true);
        f.setSize(screenSize);


    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });
    }
}
