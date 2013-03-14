package nl.raymondvermaas.master.expertconsensus;


public class Expert {
    
    private String name;
    private Ranking ranking;

    public Expert(String name, Ranking ranking) {
        this.name = name;
        this.ranking = ranking;
    }
    
    public Expert(String name) {
        this.name = name;       
    }

    public String getName() {
        return name;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }
    
}
