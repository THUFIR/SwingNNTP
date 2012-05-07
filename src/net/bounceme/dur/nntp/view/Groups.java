package net.bounceme.dur.nntp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.swing.DefaultListModel;
import net.bounceme.dur.nntp.controller.Usenet;

public class Groups extends javax.swing.JPanel {

    private static final Logger LOG = Logger.getLogger(Groups.class.getName());
    private List<Folder> folders = new ArrayList<Folder>();
    private DefaultListModel defaultListModel;
    private Usenet usenet = Usenet.INSTANCE;

    public Groups() {
        setDefaultListModel(usenet.getFoldersListModel());
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
        groups = new javax.swing.JList();

        groups.setModel(defaultListModel);
        groups.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        groups.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                groupsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(groups);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(237, 237, 237)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(355, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void groupsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_groupsValueChanged
        String s = groups.getSelectedValue().toString();
        LOG.fine(s);
        usenet.setGroup(s);
        firePropertyChange(s, s, s);
    }//GEN-LAST:event_groupsValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList groups;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public DefaultListModel getDefaultListModel() {
        return defaultListModel;
    }

    public void setDefaultListModel(DefaultListModel defaultListModel) {
        this.defaultListModel = defaultListModel;
    }
}
