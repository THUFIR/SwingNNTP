package net.bounceme.dur.nntp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class News {

    private static final Logger LOG = Logger.getLogger(News.class.getName());
    static JFrame frame = new JFrame();
    static JTextPane text = new JTextPane();
    static JSlider slider = new JSlider();
    static MessagesController messagesController = new MessagesController();
    static DefaultTableModel defaultTableModel = new DefaultTableModel();
    static JTable table = new JTable();

    private static void createAndShowGUI() {
        defaultTableModel = messagesController.getDefaultTableModel();
        table.setModel(defaultTableModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().setSelectionInterval(1, 1);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                row = Math.abs(row);  //how can this be negative?
                LOG.fine("row " + row);
                MessageBean messageBean = messagesController.getMessageBean(row);
                text.setText(messageBean.getContent());
                text.setContentType("text/html");
            }
        });
        slider.setMinimum(1);
        slider.setMaximum(messagesController.getMax());
        slider.setValue(messagesController.getMax());
        slider.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                int index = slider.getValue();
                LOG.fine("slider " + index);
                messagesController.setIndex(index);
                defaultTableModel = messagesController.getDefaultTableModel();
                table.setModel(defaultTableModel);
                table.getSelectionModel().setSelectionInterval(1, 1);
            }
        });


        JEditorPane note = new JEditorPane();
        note.setSize(50, 500);
        note.setText("foo");
        JButton add = new JButton("note");

        Action sendAction = new AbstractAction("Send") {

            public void actionPerformed(ActionEvent ae) {
                int row = table.getSelectedRow();
                MessageBean messageBean = messagesController.getMessageBean(row);
                int id = messageBean.getId();
            }
        };


        add.setAction(sendAction);
        text.setAction(sendAction);



        JPanel p = new JPanel();
        p.add(note, BorderLayout.CENTER);
        p.add(add, BorderLayout.SOUTH);
        frame.add(table, BorderLayout.WEST);
        frame.add(text, BorderLayout.CENTER);
        frame.add(p, BorderLayout.EAST);
        frame.add(slider, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.pack();

        frame.setVisible(
                true);
        frame.setSize(screenSize);
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });
    }
}
