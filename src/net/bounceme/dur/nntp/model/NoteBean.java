package net.bounceme.dur.nntp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "NoteBean")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NoteBean.findAll", query = "SELECT n FROM NoteBean n"),
    @NamedQuery(name = "NoteBean.findById", query = "SELECT n FROM NoteBean n WHERE n.id = :id"),
    @NamedQuery(name = "NoteBean.findByStamp", query = "SELECT n FROM NoteBean n WHERE n.stamp = :stamp"),
    @NamedQuery(name = "NoteBean.findByMessageId", query = "SELECT n FROM NoteBean n WHERE n.messageId = :messageId")})
public class NoteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(NoteBean.class.getName());
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "STAMP", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date stamp;
    @Lob
    @Column(name = "NOTE", length = 2147483647)
    private String note;
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID", nullable = false)
    private int messageId;

    public NoteBean() {
        LOG.fine("no args");
    }

    public NoteBean(Long id) {
        LOG.fine("id");
        this.id = id;
    }

    public NoteBean(Long id, Date stamp, int messageId) {
        LOG.fine("id, stamp, messageId");
        this.id = id;
        this.stamp = stamp;
        this.messageId = messageId;
    }

    public NoteBean(Message message, String noteString) {
        LOG.info("message, noteString");
        setMessageId(message.getMessageNumber());
        setNote(noteString);
        setStamp(new Date());
        LOG.info(toString());
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        LOG.info(String.valueOf(messageId));
        this.messageId = messageId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NoteBean)) {
            return false;
        }
        NoteBean other = (NoteBean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NoteBean " + getId() + " " + getMessageId();
    }
}
