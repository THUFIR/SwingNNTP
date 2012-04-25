package net.bounceme.dur.nntp;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;

public enum EnumNNTP {

    INSTANCE;
    private final Logger log = Logger.getLogger(EnumNNTP.class.getName());
    private Properties props = new Properties();
    private List<Message> messages = new ArrayList<Message>();
    private Message message;
    private boolean loaded = false;
    private int index = 0;   //hmmm, offset by two
    private Folder folder = null;
    private Folder root = null;
    private Store store = null;
    private int min;
    private int max;

    private EnumNNTP() {
        log.info("SingletonNNTP..only once...");
        props = PropertiesReader.getProps();
        if (!loaded) {
            try {
                loaded = connect();
            } catch (Exception ex) {
                Logger.getLogger(EnumNNTP.class.getName()).log(Level.SEVERE, "FAILED TO LOAD MESSAGES", ex);
            }
        }
    }

    public List<Message> getMessages(boolean debug) throws Exception {
        log.info("SingletonNNTP.getMessages..");
        return Collections.unmodifiableList(messages);
    }

    private boolean connect() throws Exception {
        log.info("SingletonNNTP.connect..");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        store = session.getStore(new URLName(props.getProperty("nntp.host")));
        store.connect();
        root = store.getDefaultFolder();
        folder = root.getFolder(props.getProperty("nntp.group"));
        folder.open(Folder.READ_ONLY);

        setIndex(folder.getMessageCount());
        index = index + 10;
        page(true);
        return true;
    }

    public Message getMessage() throws Exception {
        log.info("SingletonNNTP.getMessage..");
        for (Message m : messages) {
            if (m.getMessageNumber() == index) {
                message = m;
                log.info("SingletonNNTP.getMessage found " + index);
            }
        }
        return message;
    }

    public int getIndex() {
        log.log(Level.INFO, "SingletonNNTP.getIndex..{0}", index);
        return index;
    }

    public void setIndex(int index) throws Exception {
        log.log(Level.INFO, "SingletonNNTP.setIndex..{0}", index);
        this.index = index;
    }

    public void page(boolean isBack) throws Exception {
        log.log(Level.INFO, "SingletonNNTP.page..{0}", index);
        int difference = isBack ? -10 : 10;
        setIndex(index + difference);
        Message[] msgs = folder.getMessages(index - 10, index);
        messages = Arrays.asList(msgs);
        Collections.reverse(messages);
        message = messages.get(0);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
