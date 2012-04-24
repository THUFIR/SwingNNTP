package net.bounceme.dur.nntp;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.table.DefaultTableModel;

public class MessageUtils {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageUtils.class.getName());
    private static DefaultTableModel dataTableModel = new DefaultTableModel();
    private static EnumNNTP nntp = EnumNNTP.INSTANCE;
    private static Vector messages = new Vector();

    public static void loadMessages() throws Exception {
        List<Message> listOfMessages = nntp.getMessages(false);//nntp debug off
        messages.clear();
        for (Message m : listOfMessages) {
            MessageBean messageBean = new MessageBean(m);
            messages.add(messageBean);
        }
        loadTableModel();
    }

    private static void loadTableModel() {
        dataTableModel = new DefaultTableModel();
        dataTableModel.addColumn("from");
        dataTableModel.addColumn("sent");
        dataTableModel.addColumn("subject");
        dataTableModel.addColumn("content");
        for (Object o : messages) {  //awkward Vector manipulation
            MessageBean messageBean = (MessageBean) o;
            Vector messageBeanAsVector = messageBean.getVector();
            dataTableModel.addRow(messageBeanAsVector);
        }
    }

    public static DefaultTableModel getDataTableModel() {
        Vector vector = dataTableModel.getDataVector();
        Iterator it = vector.iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            Iterator i = v.iterator();
            while (i.hasNext()) {
                LOG.info(i.next().toString());
            }
        }
        return dataTableModel;
    }

    public static void setDataTableModel(DefaultTableModel dataTableModel) {
        MessageUtils.dataTableModel = dataTableModel;
    }

    public static Vector getMessages() {
        return messages;
    }

    public static void setMessages(Vector messages) {
        MessageUtils.messages = messages;
    }
}
