package net.bounceme.dur.nntp.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.swing.DefaultListModel;

public enum NewsGroups {

    INSTANCE;
    private final Logger LOG = Logger.getLogger(NewsGroups.class.getName());
    private DefaultListModel foldersListModel;

    NewsGroups() {
    }

    public void loadFoldersList(List<Folder> folders) {
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

    public void setFoldersListModel(DefaultListModel foldersListModel) {
        this.foldersListModel = foldersListModel;
    }
}
