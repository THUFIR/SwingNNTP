package net.bounceme.dur.nntp;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;

public class NewsServer {

    private static final Logger LOG = Logger.getLogger(NewsServer.class.getName());
    private Properties props = new Properties();
    private List<Message> messages = new ArrayList<Message>();
    private boolean loaded = false;
    private Folder folder = null;
    private Folder root = null;
    private Store store = null;
    private int size;

    public NewsServer() {
        LOG.info("SingletonNNTP..only once...");
        props = PropertiesReader.getProps();
        if (!loaded) {
            try {
                loaded = connect();
            } catch (Exception ex) {
                Logger.getLogger(NewsServer.class.getName()).log(Level.SEVERE, "FAILED TO LOAD MESSAGES", ex);
            }
        }
    }

    private boolean connect() throws Exception {
        LOG.info("SingletonNNTP.connect..");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        store = session.getStore(new URLName(props.getProperty("nntp.host")));
        store.connect();
        root = store.getDefaultFolder();
        folder = root.getFolder(props.getProperty("nntp.group"));
        folder.open(Folder.READ_ONLY);
        setSize(folder.getMessageCount());
        return true;
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public List<Message> getMessages(int start, int end) {
        try {
            messages = Arrays.asList(folder.getMessages(start, end));
        } catch (MessagingException ex) {
            Logger.getLogger(NewsServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.unmodifiableList(messages);
    }
}