package net.bounceme.dur.nntp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.table.DefaultTableModel;

public class MessagesController {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessagesController.class.getName());
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private NewsServer nntp = new NewsServer();
    private List<Message> messages = new ArrayList<Message>();
    private int pageSize = 10;
    private int index;
    private int max;

    public MessagesController() {
        setMax(nntp.getSize() - 10);
        setIndex(getMax() - 10);
        loadTableModel();
    }

    private void loadTableModel() {
        messages = nntp.getMessages(getIndex() - getPageSize(), getIndex());
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("sent");
        dtm.addColumn("subject");
        for (Message message : messages) {
            MessageBean messageBean = new MessageBean(message);
            Vector messageBeanAsVector = messageBean.getVector();
            dtm.addRow(messageBeanAsVector);
        }
        setDefaultTableModel(dtm);
    }

    public void logModel() {
        Vector vector = defaultTableModel.getDataVector();
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

    public void setDefaultTableModel(DefaultTableModel dataTableModel) {
        this.defaultTableModel = dataTableModel;
        logModel();
    }

    public MessageBean getMessageBean(int row) {
        Message message = messages.get(row);
        MessageBean messageBean = new MessageBean(message);
        return messageBean;
    }

    public int getIndex() {
        LOG.info(String.valueOf(index));
        return index;
    }

    public void setIndex(int index) {
        LOG.log(Level.INFO, "trying {0}", index);
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
}
