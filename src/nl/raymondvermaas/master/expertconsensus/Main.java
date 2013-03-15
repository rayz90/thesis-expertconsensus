package nl.raymondvermaas.master.expertconsensus;

import java.util.ArrayList;

import nl.raymondvermaas.master.expertconsensus.io.Import;
import nl.raymondvermaas.master.expertconsensus.io.ImportResultCsv;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Import importer = new ImportResultCsv(
				"/home/raymondv/Documents/weights.csv");
		((ImportResultCsv) importer).read();
		Ranking initRank = importer.getCriteria();
		ArrayList<Expert> experts = (ArrayList<Expert>) importer.getExperts();

		ExpertConsensus ec = new ExpertConsensus(experts, initRank);
		ec.solve();
		ec.printResults();
	}

}
