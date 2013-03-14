package nl.raymondvermaas.master.expertconsensus.io;

import java.util.ArrayList;
import java.util.List;
import nl.raymondvermaas.master.expertconsensus.Criterion;
import nl.raymondvermaas.master.expertconsensus.Expert;
import nl.raymondvermaas.master.expertconsensus.Ranking;

public class ImportTest implements Import{

    private Ranking criteria=null;
    private ArrayList<Expert> experts=null;
    
    
    
    @Override
    public List<Expert> getExperts() {
        if(this.experts != null) return this.experts;
        experts = new ArrayList<Expert>();
        
        // Expert 1
        Ranking rankex1 = criteria.deepCopy();
        experts.add(new Expert("expert 1", rankex1));
        
        // Expert 2
        Ranking rankex2 = criteria.deepCopy();
        rankex2.flip(0, 1);
        experts.add(new Expert("expert 2", rankex2));
        
        // Expert 3
        Ranking rankex3 = criteria.deepCopy();
        rankex3.flip(0,4);
        rankex3.flip(1,3);
        experts.add(new Expert("expert 3", rankex3));
        
        return experts;             
    }

    @Override
    public Ranking getCriteria() {
        if(this.criteria != null) return this.criteria;
        this.criteria = new Ranking();
        criteria.add(new Criterion("Time", 1));
        criteria.add(new Criterion("Expertise", 2));
        criteria.add(new Criterion("Knowledge", 3));
        criteria.add(new Criterion("Opportunity", 4));
        criteria.add(new Criterion("Equipment", 5));
        
        this.criteria = criteria;
        return criteria;
    }


    
}
