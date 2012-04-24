package net.bounceme.dur.nntp;

import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.table.DefaultTableModel;

public class MessageUtils {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageUtils.class.getName());
    private static DefaultTableModel dtm = new DefaultTableModel();
    private static EnumNNTP nntp = EnumNNTP.INSTANCE;
    private static Vector messages = new Vector();

    public static void loadMessages() throws Exception {
        List<Message> msgs = nntp.getMessages(false);
        messages.clear();
        for (Message m : msgs) {
            MessageBean mb = new MessageBean(m);
            messages.add(mb);
        }
        loadTableModel();
    }

    private static void loadTableModel() {
        dtm = new DefaultTableModel();
        dtm.addColumn("from");
        dtm.addColumn("sent");
        dtm.addColumn("subject");
        dtm.addColumn("content");
        for (Object o : messages) {
            MessageBean m = (MessageBean) o;
            Vector v = m.getVector();
            dtm.addRow(v);
        }
    }

    public static DefaultTableModel getDataTableModel() {
        return dtm;
    }

    public static void setDataTableModel(DefaultTableModel dataTableModel) {
        MessageUtils.dtm = dataTableModel;
    }

    public static Vector getMessages() {
        return messages;
    }

    public static void setMessages(Vector messages) {
        MessageUtils.messages = messages;
    }
}
