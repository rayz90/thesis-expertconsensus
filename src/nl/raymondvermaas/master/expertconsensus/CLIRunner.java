package nl.raymondvermaas.master.expertconsensus;

import java.util.ArrayList;

import nl.raymondvermaas.master.expertconsensus.io.Import;
import nl.raymondvermaas.master.expertconsensus.io.ImportResultCsv;
import nl.raymondvermaas.master.expertconsensus.io.XMLWriter;

public class CLIRunner {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: <file.csv> <base.xml> [startCritId]");
			return;
		}

		String csvfile = args[0];
		String xmlfile = args[1];
		int startCrit = Integer.parseInt(args[2]);

		Import importer = new ImportResultCsv(csvfile, 9, 5);
		((ImportResultCsv) importer).read();
		Ranking initRank = importer.getCriteria();
		ArrayList<Expert> experts = (ArrayList<Expert>) importer.getExperts();

		ExpertConsensus ec = new ExpertConsensus(experts, initRank);
		ec.solve();
		ec.printResults();

		XMLWriter xml = new XMLWriter(ec.getBestRanking(), xmlfile, startCrit);
		xml.write();
	}

}
