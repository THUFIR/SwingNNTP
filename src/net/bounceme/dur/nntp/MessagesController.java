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
    private DefaultTableModel dtm = new DefaultTableModel();
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
        List<Message> messages = nntp.getMessages(getIndex() - getPageSize(), getIndex());
        dtm = null;
        dtm = new DefaultTableModel();
        dtm.addColumn("sent");
        dtm.addColumn("subject");
        for (Message message : messages) {
            MessageBean messageBean = new MessageBean(message);
            LOG.fine("MessagesController.loadTableModel.." + messageBean.toString());
            Vector messageBeanAsVector = messageBean.getVector();
            dtm.addRow(messageBeanAsVector);
        }
    }

    public DefaultTableModel getDefaultTableModel() {
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
        return dtm;
    }

    public void setDefaultTableModel(DefaultTableModel dataTableModel) {
        this.dtm = dataTableModel;
    }

    public MessageBean getMessageBean(int row) {
        Message message = messages.get(row);
        MessageBean messageBean = new MessageBean(message);
        return messageBean;
    }

    public int getIndex() {
        LOG.log(Level.INFO, "MessagesController.getIndex..{0}", index);
        return index;
    }

    public void setIndex(int index) {
        LOG.log(Level.INFO, "MessagesController.setIndex..to {0}", index);
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
