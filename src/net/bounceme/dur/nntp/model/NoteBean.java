package net.bounceme.dur.nntp.model;

//merge to master

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "NOTEBEAN", catalog = "nntp", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NoteBean.findAll", query = "SELECT n FROM NoteBean n"),
    @NamedQuery(name = "NoteBean.findById", query = "SELECT n FROM NoteBean n WHERE n.id = :id"),
    @NamedQuery(name = "NoteBean.findByStamp", query = "SELECT n FROM NoteBean n WHERE n.stamp = :stamp"),
    @NamedQuery(name = "NoteBean.findByNote", query = "SELECT n FROM NoteBean n WHERE n.note = :note")
})
public class NoteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "STAMP")
    @Temporal(TemporalType.DATE)
    private Date stamp;
    @Column(name = "NOTE", length = 255)
    private String note;

    public NoteBean() {
    }

    public NoteBean(Long id) {
        this.id = id;
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
        return "net.bounceme.dur.nntp.model.NoteBean[ id=" + id + " ]";
    }
}
