package net.bounceme.dur.nntp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class NoteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(NoteBean.class.getName());
    @Column
    private String note;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date stamp = new Date();
    @Id
    private Long id;

    public NoteBean() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return getNote() + " dated:" + getStamp();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }
}
