package nl.raymondvermaas.master.expertconsensus;

public class Criterion {

	private String name;
	private int id;

	public Criterion(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
