package net.bounceme.dur.nntp;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Simple {

    public static void main(String args[]) {
        F f = new F();
    }
}

class F extends JFrame {
    //EventQueue.invokeLater(new Runnable() {

    JTextPane text = new JTextPane();
    JSlider slider = new JSlider();
    MessagesController mc = new MessagesController();
    DefaultTableModel dtm = new DefaultTableModel();//mc.getDataTableModel();
    JTable table = new JTable();

    public F() {


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
        add(panel);
        table.revalidate();
        table.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setVisible(true);
        setSize(screenSize);
    }
}