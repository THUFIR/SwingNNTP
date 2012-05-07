package net.bounceme.dur.nntp.view;

import javax.swing.JTabbedPane;

public class MyTabs extends JTabbedPane {

    private Overview overview = new Overview();
    private Detail details = new Detail();
    private Groups groups = new Groups();

    public MyTabs() {
        this.add("groups", groups);
        this.add("overview", overview);
        this.add("detail", details);
    }
}
