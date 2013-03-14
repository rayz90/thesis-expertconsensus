package nl.raymondvermaas.master.expertconsensus;

import com.google.common.collect.Collections2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nl.raymondvermaas.master.expertconsensus.Ranking.Results;

public class ExpertConsensus {

	/* Initial parameters */
	private ArrayList<Expert> experts;
	private Ranking initRank;
	
	private Ranking bestRanking = null;
	private double bestDistance = 0;
	private double[] bestIndividualdistance;
	
    public ExpertConsensus(ArrayList<Expert> experts, Ranking initRank) {
		super();
		this.experts = experts;
		this.initRank = initRank;
		this.bestRanking = null;
		this.bestDistance = 0;
		this.bestIndividualdistance = new double[experts.size()];
	}

	public void solve() {
        Collection<List<Criterion>> permutations = Collections2.permutations(initRank);
        
        bestDistance = 0;
        bestRanking = initRank;
        bestIndividualdistance = new double[experts.size()];
        for (List<Criterion> permutation : permutations) {
			Ranking ranking = new Ranking();
			ranking.addAll(permutation);

			double totaldistance = 0.0;
			double[] individualdistance = new double[experts.size()];
			int i = 0;
			for (Expert expert : experts) {
				Results result = ranking.compareRanking(expert.getRanking());
				double distance = ranking.size() * (ranking.size() - 1)
						- result.getTotal();
				individualdistance[i] = distance;
				totaldistance += distance;
				i++;
			}

			if (totaldistance < bestDistance)
				continue;
			
			if (totaldistance == bestDistance && 
					ListMath.sd(bestIndividualdistance) < ListMath.sd(individualdistance)) {
					continue;
			}
			bestDistance = totaldistance;
			bestRanking = ranking;
			bestIndividualdistance = individualdistance;

		}
        
    }
    
    public void printResults() {
    	if (bestRanking == null) return;
        System.out.println("Best Distance:" + bestDistance + "\n");
        System.out.println(bestRanking.toString());
        
        
        System.out.println("Individual score:");
        for (int i=0;i<bestIndividualdistance.length;i++) {
            System.out.println(experts.get(i).getName() + " - " + bestIndividualdistance[i]);
        }
    }

	public ArrayList<Expert> getExperts() {
		return experts;
	}

	public void setExperts(ArrayList<Expert> experts) {
		this.experts = experts;
	}

	public Ranking getInitRank() {
		return initRank;
	}

	public void setInitRank(Ranking initRank) {
		this.initRank = initRank;
	}

	public Ranking getBestRanking() {
		return bestRanking;
	}

	public double getBestDistance() {
		return bestDistance;
	}

	public double[] getBestIndividualdistance() {
		return bestIndividualdistance;
	}   
    
}
