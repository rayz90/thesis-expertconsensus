package nl.raymondvermaas.master.expertconsensus.io;

import java.util.List;
import nl.raymondvermaas.master.expertconsensus.Expert;
import nl.raymondvermaas.master.expertconsensus.Ranking;

/**
 *
 * @author Raymond
 */
public interface Import {
    
    public List<Expert> getExperts();
    
    public Ranking getCriteria();
}
