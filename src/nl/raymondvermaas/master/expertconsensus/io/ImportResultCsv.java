package nl.raymondvermaas.master.expertconsensus.io;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.com.bytecode.opencsv.CSVReader;

import nl.raymondvermaas.master.expertconsensus.Criterion;
import nl.raymondvermaas.master.expertconsensus.Expert;
import nl.raymondvermaas.master.expertconsensus.Ranking;

public class ImportResultCsv implements Import{
	private int offset;
	private int numberOfCriteria;

	protected Ranking criteria = new Ranking();
    protected ArrayList<Expert> experts = new ArrayList<Expert>();
    protected CSVReader reader;
    
	public ImportResultCsv(String filename, int offset, int numberOfCriteria) {
		try {
            reader = new CSVReader(new FileReader(filename), ',');
        } catch (Exception ex) {
            Logger.getLogger(ImportResultCsv.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
			this.offset = offset;
			this.numberOfCriteria = numberOfCriteria;       
	}
	
	public ImportResultCsv(String filename) {
        this(filename,0,5);
    }  

	public void read() {
		try {	       
            String [] nextLine = reader.readNext();
            nextLine = reader.readNext();
            for(int i=offset;i<offset+numberOfCriteria;i++) {
                criteria.add(new Criterion(nextLine[i],i+1-offset));
            }
            
            int expertid=1;
            while ((nextLine = reader.readNext()) != null) {
            	if (nextLine[offset].isEmpty()) {
            		expertid++;
            		continue;
            	}
                Ranking ranking = criteria.deepCopy();
                for(int i=offset;i<offset+numberOfCriteria;i++) {
                    int new_position = Integer.parseInt(nextLine[i])-1;
                    int currrent_position = ranking.find(i+1-offset);
                    ranking.flip(currrent_position, new_position);
                }
                experts.add(new Expert("Expert "+expertid, ranking));
                expertid++;
            }
        } catch (Exception ex) {
            Logger.getLogger(ImportResultCsv.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }		
	}

    @Override
    public List<Expert> getExperts() {
        return this.experts;
    }

    @Override
    public Ranking getCriteria() {
        return this.criteria;
    }
    
}
