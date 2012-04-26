package net.bounceme.dur.nntp;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Simple {

    private static JFrame frame = new JFrame();
    private static JTextPane text = new JTextPane();
    private static JSlider slider = new JSlider();
    private static MessagesController mc = new MessagesController();
    private static DefaultTableModel dtm = mc.getDataTableModel();
    private static JTable table = new JTable();

    /*
     * EventQueue.invokeLater(new Runnable() { /* your stuff here
     */
    public static void main(String args[]) {
        //EventQueue.invokeLater(new Runnable() {

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
                table.revalidate();
                table.repaint();
                frame.revalidate();
                frame.repaint();
            }
        });

        table.getSelectionModel().setSelectionInterval(1, 1);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(table);
        panel.add(text);
        panel.add(slider);
        frame.add(panel);
        table.revalidate();
        table.repaint();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(screenSize);
        //}
    }
}
