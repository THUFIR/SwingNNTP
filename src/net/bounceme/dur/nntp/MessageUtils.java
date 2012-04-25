package net.bounceme.dur.nntp;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.table.DefaultTableModel;

public class MessageUtils {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageUtils.class.getName());
    private static DefaultTableModel defaultTableModel = new DefaultTableModel();
    private static EnumNNTP nntp = EnumNNTP.INSTANCE;
    private static Vector messages = new Vector();
    private static int pageSize = 10;
    private static int index;
    private static int max;

    public static void init() {
        max = nntp.getSize();
        index = max - 10;
        loadMessages();
    }

    private static void loadMessages() {
        List<Message> listOfMessages = nntp.getMessages(index - pageSize, index);
        for (Message m : listOfMessages) {
            MessageBean messageBean = null;
            try {
                messageBean = new MessageBean(m);
            } catch (Exception ex) {
                Logger.getLogger(MessageUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            messages.add(messageBean);
        }
        loadTableModel();
    }

    private static void loadTableModel() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("sent");
        defaultTableModel.addColumn("subject");
        for (Object o : messages) {  //awkward Vector manipulation
            MessageBean messageBean = (MessageBean) o;
            Vector messageBeanAsVector = messageBean.getVector();
            defaultTableModel.addRow(messageBeanAsVector);
        }
    }

    public static DefaultTableModel getDataTableModel() {
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

    public static void setDataTableModel(DefaultTableModel dataTableModel) {
        MessageUtils.defaultTableModel = dataTableModel;
    }

    public static Vector getMessages() {
        return messages;
    }

    public static void setMessages(Vector messages) {
        MessageUtils.messages = messages;
    }

    public static MessageBean getMessageBean(int row) {
        MessageBean messageBean = (MessageBean) messages.elementAt(row);
        return messageBean;
    }

    public static void page(boolean isBack) {
        LOG.log(Level.INFO, "MessageUtils.page..{0}", isBack);
        index = isBack ? index - 10 : index + 10;
        index = index < pageSize + 1 ? pageSize + 1 : index;  //to prevent negative pages
        index = index > max ? max - pageSize : index;  //to prevent paging too far
        LOG.log(Level.INFO, "MessageUtils.page..{0}", index);
        loadMessages();
    }
}
