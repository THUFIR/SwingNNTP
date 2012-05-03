package net.bounceme.dur.nntp;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.table.DefaultTableModel;

public enum MessagesEnum {

    INSTANCE;
    private final long serialVersionUID = 1L;
    private final Logger LOG = Logger.getLogger(MessagesEnum.class.getName());
    private DefaultTableModel defaultTableModel = null;
    private NewsServer nntp = new NewsServer();
    private List<Message> messages = new ArrayList<Message>();
    private int pageSize = 35;
    private int index;
    private int max;
    private Map<Integer, String> notes = new HashMap<Integer, String>();

    private MessagesEnum() {
        LOG.info("starting controller..");
        setMax(nntp.getSize() - 10);
        setIndex(getMax() - 10);
        loadTableModel();
    }

    private void loadTableModel() {
        messages = nntp.getMessages(getIndex() - getPageSize(), getIndex());
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("subject");
        dtm.addColumn("id");
        for (Message message : messages) {
            MessageBean messageBean = new MessageBean(message);
            dtm.addRow(messageBean.getVector());
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
            LOG.fine(row.toString());
        }
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
        logModel(defaultTableModel);
        this.defaultTableModel = defaultTableModel;
        defaultTableModel.fireTableDataChanged();
    }

    public MessageBean getMessageBean(int row) {
        Message message = messages.get(row);
        MessageBean messageBean = new MessageBean(message);
        return messageBean;
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
}