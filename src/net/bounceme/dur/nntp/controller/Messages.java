package net.bounceme.dur.nntp.controller;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.table.DefaultTableModel;
import net.bounceme.dur.nntp.model.MessageBean;

public enum Messages {

    INSTANCE;
    private final long serialVersionUID = 1L;
    private final Logger LOG = Logger.getLogger(Messages.class.getName());
    private DefaultTableModel defaultTableModel = null;
    private Usenet usenet = Usenet.INSTANCE;
    private List<Message> messages = new ArrayList<Message>();
    private int pageSize = 35;
    private int index;
    private int max;
    private Map<Integer, String> notes = new HashMap<Integer, String>();
    private Message message;
    private MessageBean messageBean;

    private Messages() {
        LOG.info("starting controller..");
        setMax(usenet.getSize() - 10);
        setIndex(getMax() - 10);
        loadTableModel();
    }

    private void loadTableModel() {
        messages = usenet.getMessages(getIndex() - getPageSize(), getIndex());
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("subject");
        dtm.addColumn("id");
        for (Message m : messages) {
            MessageBean mb = new MessageBean(m);
            dtm.addRow(mb.getVector());
        }
        setDefaultTableModel(dtm);
    }

    public void logModel(DefaultTableModel dtm) {
        Vector vector = dtm.getDataVector();
        Iterator it = vector.iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            Iterator i = v.iterator();
            StringBuilder row = new StringBuilder();
            while (i.hasNext()) {
                row.append(i.next());
            }
            LOG.info(row.toString());
        }
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
        this.defaultTableModel = defaultTableModel;
        defaultTableModel.fireTableDataChanged();
    }

    public int getIndex() {
        LOG.fine(String.valueOf(index));
        return index;
    }

    public void setIndex(int index) {
        LOG.log(Level.FINE, "trying {0}", index);
        this.index = index;
        loadTableModel();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<Integer, String> getNotes() {
        return notes;
    }

    public void setNotes(Map<Integer, String> notes) {
        this.notes = notes;
    }

    public void addNote(Integer i, String s) {
        notes.put(i, s);
    }

    public String getNote(int i) {
        String s = null;
        try {
            s = notes.get(i);
        } catch (Exception e) {
            s = "no note yet";
        }
        return s;
    }

    public Message getMessage() {
        return message;
    }

    private void setMessage(Message message) {
        this.message = message;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(int row) {
        setMessage(messages.get(row));
        messageBean = new MessageBean(getMessage());
    }

    public void setGroup(String group) {
        LOG.fine("changing group..");
        usenet.setGroup(group);
        setMax(usenet.getSize() - 10);
        setIndex(getMax() - 10);
        loadTableModel();
    }
}