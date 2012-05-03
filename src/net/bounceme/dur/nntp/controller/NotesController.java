package net.bounceme.dur.nntp.controller;

import java.util.*;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.swing.DefaultListModel;
import net.bounceme.dur.nntp.model.NoteBean;

public class NotesController {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(NotesController.class.getName());
    private Message message;
    private List<NoteBean> notes =  new ArrayList<NoteBean>();
    private DefaultListModel defaultListModel = new DefaultListModel();

    public NotesController() {
    }

    public Message getMessage() {
        return message;
    }

    public void addNote(NoteBean noteBean){
        LOG.info(noteBean.toString());
        notes.add(noteBean);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private List<NoteBean> getNotes() {
        return notes;
    }

    private void setNotes(List<NoteBean> notes) {
        this.notes = notes;
    }

    public DefaultListModel getDefaultListModel() {
        return defaultListModel;
    }

    public void setDefaultListModel(DefaultListModel defaultListModel) {
        this.defaultListModel = defaultListModel;
    }
}
