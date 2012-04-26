package net.bounceme.dur.nntp;

//dummy
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Simple {

    private static JFrame f = new JFrame();
    private static JTextPane text = new JTextPane();
    private static JSlider slider = new JSlider();
    private static DefaultTableModel dtm = MessageUtils.getDataTableModel();
    private static JTable table = new JTable();

    public static void main(String args[]) {
        MessageUtils.init();
        table.setModel(dtm);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent evt) {
                int row = table.getSelectedRow();
                MessageBean mb = MessageUtils.getMessageBean(row);
                text.setText(mb.getContent());
                text.setContentType("text/html");
                text.revalidate();
                text.repaint();
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



        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(table);
        p.add(text);
        p.add(slider);
        f.add(p);
        table.revalidate();
        table.repaint();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize(screenSize);
        f.pack();
        f.setVisible(true);
    }
}
