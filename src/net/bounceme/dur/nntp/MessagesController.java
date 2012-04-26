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
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private NewsServer nntp = new NewsServer();
    private Vector messages = new Vector();
    private int pageSize = 10;
    private int index;
    private int max;

    void mutate(Object[] obj) {
        obj[0] = "test";
        obj = new Object[]{"nope"};
    }

    void main() {
        Object[] obj = new Object[1];
        System.out.println(obj[0]);
        /*
         * prints: null
         */
        mutate(obj);
        System.out.println(obj[0]);
        /*
         * prints: "test"
         */
    }

    public MessagesController() {
        setMax(nntp.getSize());
        setIndex(getMax() - 10);
        loadMessages();
    }

    private void loadMessages() {
        List<Message> listOfMessages = nntp.getMessages(getIndex() - pageSize, getIndex());
        for (Message m : listOfMessages) {
            MessageBean messageBean = null;
            try {
                messageBean = new MessageBean(m);
            } catch (Exception ex) {
                Logger.getLogger(MessagesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            messages.add(messageBean);
        }
        loadTableModel();
    }

    private void loadTableModel() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("sent");
        defaultTableModel.addColumn("subject");
        for (Object o : messages) {  //awkward Vector manipulation
            MessageBean messageBean = (MessageBean) o;
            Vector messageBeanAsVector = messageBean.getVector();
            defaultTableModel.addRow(messageBeanAsVector);
        }
    }

    public DefaultTableModel getDataTableModel() {
        Vector vector = defaultTableModel.getDataVector();
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
        return defaultTableModel;
    }

    public void setDataTableModel(DefaultTableModel dataTableModel) {
        this.defaultTableModel = dataTableModel;
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
        return index;
    }

    public void setIndex(int aIndex) {
        LOG.log(Level.INFO, "MessageUtils.setIndex..trying {0}", aIndex);
        if (aIndex < pageSize + 1) {  //goldilocks check on aIndex
            aIndex = pageSize + 1;
            LOG.log(Level.INFO, "too small, setting default..{0}", aIndex);
        } else if (aIndex >= getMax() - pageSize) {
            aIndex = getMax() - pageSize;
            LOG.log(Level.INFO, "too big, setting default..{0}", aIndex);
        } else {
            LOG.log(Level.INFO, "just right, setting..{0}", aIndex);
        }
        index = aIndex;
        loadMessages();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int aMax) {
        max = aMax;
    }
}
