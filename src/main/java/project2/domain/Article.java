package project2.domain;

public class Article {
	
	int id;
	String name;
	Double value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	public Article(int id, String name, Double value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public Article() {
		super();
	}

}
