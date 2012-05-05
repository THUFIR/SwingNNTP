package net.bounceme.dur.nntp.controller;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.swing.DefaultListModel;
import net.bounceme.dur.nntp.model.PropertiesReader;

public enum Usenet {

    INSTANCE;
    private final Logger LOG = Logger.getLogger(Usenet.class.getName());
    private Properties props = new Properties();
    private List<Message> messages = new ArrayList<Message>();
    private DefaultListModel foldersListModel = new DefaultListModel();
    private boolean loaded = false;
    private Folder folder = null;
    private Folder root = null;
    private Store store = null;
    private int size;

    Usenet() {
        LOG.fine("Usenet..only once...");
        props = PropertiesReader.getProps();
        if (!loaded) {
            try {
                loaded = connect();
            } catch (Exception ex) {
                Logger.getLogger(Usenet.class.getName()).log(Level.SEVERE, "FAILED TO LOAD MESSAGES", ex);
            }
        }
    }

    private boolean connect() throws Exception {
        LOG.fine("Usenet.connect..");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        store = session.getStore(new URLName(props.getProperty("nntp.host")));
        store.connect();
        root = store.getDefaultFolder();
        Folder[] f = root.list();
        LOG.warning("folders array " + f.length);
        loadFoldersList(Arrays.asList(root.list()));
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

    private void logMessages() throws MessagingException {
        LOG.fine("NewsServer.logMessages..");
        for (Message m : messages) {
            LOG.log(Level.FINER, "***** {0}  {1}", new Object[]{m.getMessageNumber(), m.getSubject()});
        }
    }

    public List<Message> getMessages(int start, int end) {
        LOG.log(Level.FINE, "NewsServer.getMessages {0} {1}", new Object[]{start, end});
        try {
            messages = Arrays.asList(folder.getMessages(start, end));
            Collections.reverse(messages);
            logMessages();
        } catch (MessagingException ex) {
            Logger.getLogger(Usenet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Collections.unmodifiableList(messages);
    }

    private void loadFoldersList(List<Folder> folders) {
        LOG.log(Level.WARNING, "folders {0}", folders.size());
        DefaultListModel dlm = new DefaultListModel();
        for (Folder f : folders) {
            String name = f.getName();
            dlm.addElement(name);
            LOG.warning(name);
        }
        setFoldersListModel(dlm);
    }

    public DefaultListModel getFoldersListModel() {
        LOG.warning(foldersListModel.toString());
        return foldersListModel;
    }

    private void setFoldersListModel(DefaultListModel foldersListModel) {
        this.foldersListModel = foldersListModel;
    }

}
