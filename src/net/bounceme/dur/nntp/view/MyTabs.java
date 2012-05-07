package net.bounceme.dur.nntp.view;

import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import net.bounceme.dur.nntp.controller.Usenet;

public class MyTabs extends JTabbedPane {

    private static final Logger LOG = Logger.getLogger(MyTabs.class.getName());
    private Overview overview = new Overview();
    private Detail details = new Detail();
    private Groups groups = new Groups();

    public MyTabs() {
        this.add("groups", groups);
        this.add("overview", overview);
        this.add("detail", details);

        groups.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String s = evt.getNewValue().toString();
                LOG.fine(s);
                overview = new Overview();
            }

        });

    }
}
