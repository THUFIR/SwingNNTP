/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bounceme.dur.nntp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thufir
 */
@Entity
@Table(name = "NOTEBEAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notebean.findAll", query = "SELECT n FROM Notebean n"),
    @NamedQuery(name = "Notebean.findById", query = "SELECT n FROM Notebean n WHERE n.id = :id"),
    @NamedQuery(name = "Notebean.findByStamp", query = "SELECT n FROM Notebean n WHERE n.stamp = :stamp"),
    @NamedQuery(name = "Notebean.findByMessageId", query = "SELECT n FROM Notebean n WHERE n.messageId = :messageId")})
public class Notebean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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

    public Notebean() {
    }

    public Notebean(Long id) {
        this.id = id;
    }

    public Notebean(Long id, Date stamp, int messageId) {
        this.id = id;
        this.stamp = stamp;
        this.messageId = messageId;
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
        if (!(object instanceof Notebean)) {
            return false;
        }
        Notebean other = (Notebean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.bounceme.dur.nntp.model.Notebean[ id=" + id + " ]";
    }

}
