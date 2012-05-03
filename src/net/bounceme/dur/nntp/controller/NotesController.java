package net.bounceme.dur.nntp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import net.bounceme.dur.nntp.model.NoteBean;

public class NotesController {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(NotesController.class.getName());
    private Message message;
    private List<NoteBean> notes = new ArrayList<NoteBean>();
    private DefaultListModel defaultListModel = new DefaultListModel();
    private EntityManagerFactory emf;
    private EntityManager em;
    private String PERSISTENCE_UNIT_NAME = "nntpPU";

    public NotesController() {
        populateList();
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    private void populateList() {
        em.getTransaction().begin();
        Query q = em.createQuery("select * from Note");
        List results = q.getResultList();
        em.getTransaction().commit();
        setNotes(results);
        DefaultListModel dlm = new DefaultListModel();
        for (NoteBean n : getNotes()) {
            dlm.addElement(n);
        }
        setDefaultListModel(dlm);
    }

    public Message getMessage() {
        return message;
    }

    public void addNote(NoteBean noteBean) {
        LOG.info(noteBean.toString());
        em.getTransaction().begin();
        em.persist(noteBean);
        em.getTransaction().commit();
        populateList();
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
