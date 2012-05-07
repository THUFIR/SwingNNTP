package net.bounceme.dur.nntp.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import net.bounceme.dur.nntp.controller.Messages;
import net.bounceme.dur.nntp.controller.NotesController;
import net.bounceme.dur.nntp.model.NoteBean;

public class Detail extends javax.swing.JPanel {

    private static final Logger LOG = Logger.getLogger(Detail.class.getName());
    private Messages messagesController = Messages.INSTANCE;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private Message message;
    private NotesController notesController = new NotesController();
    private DefaultListModel defaultListModel = new DefaultListModel();

    public Detail() {
        defaultListModel = notesController.getDefaultListModel();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        notes = new javax.swing.JList();
        add = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();

        jTextPane1.setContentType("text/html");
        jScrollPane1.setViewportView(jTextPane1);

        notes.setModel(defaultListModel);
        notes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        notes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                notesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(notes);

        add.setText("add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        text.setColumns(20);
        text.setRows(5);
        jScrollPane4.setViewportView(text);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1268, 1268, 1268))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        LOG.fine(text.getText());
        NoteBean noteBean = new NoteBean(getMessage(), text.getText());
        LOG.log(Level.INFO, "trying to add note \n\n{0}\n\n", noteBean);
        notesController.addNote(noteBean);
        defaultListModel = notesController.getDefaultListModel();
        notes.setModel(defaultListModel);
    }//GEN-LAST:event_addActionPerformed

    private void notesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_notesValueChanged
        NoteBean n = (NoteBean)notes.getSelectedValue();
        LOG.info(n.toString());
        text.setText(n.getNote());
    }//GEN-LAST:event_notesValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JList notes;
    private javax.swing.JTextArea text;
    // End of variables declaration//GEN-END:variables

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
        try {
            jTextPane1.setText(message.getContent().toString());
        } catch (IOException ex) {
            Logger.getLogger(Detail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Detail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultListModel getDefaultListModel() {
        return defaultListModel;
    }

    public void setDefaultListModel(DefaultListModel defaultListModel) {
        this.defaultListModel = defaultListModel;
    }
}
