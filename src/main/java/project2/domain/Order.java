package project2.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	int id;
	Client client;
	List<Article> articles = new ArrayList<Article>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public Order(int id, Client client, List<Article> articles) {
		super();
		this.id = id;
		this.client = client;
		this.articles = articles;
	}
	
	public Order() {
		super();
	}
	
}
