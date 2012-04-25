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
    private static MessageBean messageBean = new MessageBean();
    private static int min;
    private static int max;
    private static int index;

    public static void loadMessages() {
        try {
            List<Message> listOfMessages = nntp.getMessages(false);//nntp debug off
            messages.clear();
            for (Message m : listOfMessages) {
                MessageBean messageBean = new MessageBean(m);
                messages.add(messageBean);
            }
            loadTableModel();
        } catch (Exception ex) {
            Logger.getLogger(MessageUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void loadTableModel() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("from");
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

    public static MessageBean getMessageBean(int i) {
        messageBean = (MessageBean) messages.elementAt(i);
        return messageBean;
    }

    public static void setMessageBean(MessageBean aMessageBean) {
        messageBean = aMessageBean;
    }

    public static int getMin() {
        return min;
    }

    public static void setMin(int aMin) {
        min = aMin;
    }

    public static int getMax() {
        return max;
    }

    public static void setMax(int aMax) {
        max = aMax;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int aIndex) {
        index = aIndex;
    }

    public static void page(boolean isBack){
        try {
            nntp.page(isBack);
        } catch (Exception ex) {
            Logger.getLogger(MessageUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
