package nl.raymondvermaas.master.expertconsensus;

import java.util.ArrayList;
import java.util.ListIterator;

public class Ranking extends ArrayList<Criterion> {

	public Results compareRanking(Ranking ranking) {
		Results results = new Results();
		for (ListIterator<Criterion> it = this.listIterator(); it.hasNext();) {
			Criterion crit = it.next();
			for (ListIterator<Criterion> it2 = ranking.listIterator(); it2
					.hasNext();) {
				Criterion crit2 = it2.next();
				if (crit.getId() != crit2.getId()) {
					continue;
				}

				if (it.previousIndex() < it2.previousIndex()) {
					results.amin++;
					results.bplus++;
				} else if (it.previousIndex() > it2.previousIndex()) {
					results.aplus++;
					results.bmin++;
				}

			}
		}
		return results;
	}

	public int find(int id) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getId() == id)
				return i;
		}
		return -1;
	}

	public void flip(int index1, int index2) {
		Criterion temp = this.get(index1);
		this.set(index1, this.get(index2));
		this.set(index2, temp);
	}

	public Ranking deepCopy() {
		Ranking ranking = new Ranking();
		for (Criterion crit : this) {
			ranking.add(crit);
		}
		return ranking;
	}

	@Override
	public String toString() {
		String rtrn = "Ranking:\n";
		for (int i = 0; i < this.size(); i++) {
			rtrn += (i + 1) + " - " + this.get(i).getName() + "\n";
		}
		return rtrn;
	}

	public class Results {

		public int aplus = 0;
		public int bplus = 0;
		public int amin = 0;
		public int bmin = 0;

		public int getTotal() {
			return aplus + bplus + amin + bmin;
		}
	}
}
