package net.bounceme.dur.nntp;

import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.table.DefaultTableModel;

public class MessageUtil {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageUtil.class.getName());
    private static DefaultTableModel dataTableModel = new DefaultTableModel();
    private static EnumNNTP nntp = EnumNNTP.INSTANCE;
    private static Vector messages = new Vector();

    public static void loadMessages() throws Exception {
        List<Message> msgs = nntp.getMessages(false);
        messages.clear();
        for (Message m : msgs) {
            MessageBean mb = new MessageBean(m);
            messages.add(mb);
        }
    }

    public static DefaultTableModel getDataTableModel() {
        return dataTableModel;
    }

    public static void setDataTableModel(DefaultTableModel dataTableModel) {
        MessageUtil.dataTableModel = dataTableModel;
    }

    public static Vector getMessages() {
        return messages;
    }

    public static void setMessages(Vector messages) {
        MessageUtil.messages = messages;
    }
}
