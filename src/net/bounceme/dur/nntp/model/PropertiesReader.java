package net.bounceme.dur.nntp.model;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader {

    private static final Logger logger = Logger.getLogger(PropertiesReader.class.getName());
    private static Level level = Level.INFO;
    private static Properties props = new Properties();

    public static Properties getProps() {
        logger.log(level, "NNTP.loadMessages...");
        try {
            props.load(PropertiesReader.class.getResourceAsStream("/nntp.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.getClass().getResourceAsStream("/nntp.properties");
        //logger.log(level, props.toString());
        return props;
    }
}
