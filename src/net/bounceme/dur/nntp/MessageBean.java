package net.bounceme.dur.nntp;

import java.net.URL;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

public class MessageBean {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MessageBean.class.getName());
    private Date sent;
    private String subject;
    private String content;
    private URL link;
    private Vector vector = new Vector();

    public MessageBean() {
    }

    public MessageBean(Message message) {
        try {
            setSent(message.getSentDate());
            setSubject(message.getSubject());
            setContent(message.getContent().toString());
            vector.add(getSent());
            vector.add(getSubject());
        } catch (Exception ex) {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public Vector getVector() {
        return vector;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    @Override
    public String toString(){
        return getSubject();
    }
}
