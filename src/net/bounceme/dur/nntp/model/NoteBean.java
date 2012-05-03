package net.bounceme.dur.nntp.model;

import java.util.Date;
import java.util.logging.Logger;

public class NoteBean {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(NoteBean.class.getName());
    private String note;
    private Date date = new Date();

    public NoteBean() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getNote() + " dated:" + getDate();
    }
}
