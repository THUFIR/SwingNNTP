package net.bounceme.dur.nntp;

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
    private Vector messages = new Vector();
    private int pageSize = 10;
    private int index;
    private int max;

    public MessagesController() {
        setMax(nntp.getSize() - 10);
        setIndex(getMax() - 10);
        loadMessages();
    }

    private void loadMessages() {
        List<Message> listOfMessages = nntp.getMessages(getIndex() - getPageSize(), getIndex());
        for (Message message : listOfMessages) {
            MessageBean messageBean = null;
            try {
                messageBean = new MessageBean(message);
            } catch (Exception ex) {
                LOG.log(Level.INFO, "MessagesController.loadMessages..could not populate messageBean {0}", ex);
            }
            messages.add(messageBean);
        }
        loadTableModel();
    }

    private void loadTableModel() {
        dtm = new DefaultTableModel();
        dtm.addColumn("sent");
        dtm.addColumn("subject");
        for (Object o : messages) {  //awkward Vector manipulation
            MessageBean messageBean = (MessageBean) o;
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
            //LOG.info(row.toString());
        }
        return dtm;
    }

    public void setDefaultTableModel(DefaultTableModel dataTableModel) {
        this.dtm = dataTableModel;
    }

    public Vector getMessages() {
        return messages;
    }

    public void setMessages(Vector messages) {
        this.messages = messages;
    }

    public MessageBean getMessageBean(int row) {
        MessageBean messageBean = (MessageBean) messages.elementAt(row);
        return messageBean;
    }

    public int getIndex() {
        LOG.log(Level.INFO, "MessagesController.getIndex..{0}", index);
        return index;
    }

    public void setIndex(int index) {
        LOG.log(Level.INFO, "MessagesController.setIndex..to {0}", index);
        this.index = index;
        loadMessages();
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
