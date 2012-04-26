package net.bounceme.dur.nntp;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Simple {

    private static final Logger LOG = Logger.getLogger(Simple.class.getName());
    static JFrame frame = new JFrame();
    static JTextPane text = new JTextPane();
    static JSlider slider = new JSlider();
    static MessagesController messagesController = new MessagesController();
    static DefaultTableModel defaultTableModel = new DefaultTableModel();
    static JTable table = new JTable();

    private static void createAndShowGUI() {


        table.setModel(defaultTableModel);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        int row = table.convertRowIndexToModel(table.getSelectedRow());
                        row = Math.abs(row);
                        LOG.info("Simple.creatAndShowGUI,table value changed, selected row "+ row);
                        MessageBean messageBean = messagesController.getMessageBean(row);
                        text.setText(messageBean.getContent());
                        text.setContentType("text/html");
                    }
                });
            }
        });

        slider.setMinimum(messagesController.getMax() - 50);
        slider.setMaximum(messagesController.getMax());
        slider.setValue(messagesController.getMax());
        slider.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        int index = slider.getValue();
                        LOG.info("slider index " + index);
                        messagesController.setIndex(index);
                        table.repaint();
                    }
                });
            }
        });

        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().setSelectionInterval(1, 1);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(table);
        panel.add(text);
        panel.add(slider);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.pack();
        frame.setVisible(true);
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
