package project2.service;


import java.util.List;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;

public interface DatabaseManager {
	
	public int addClient(Client client);
	public int addArticle(Article article);
	public Article findArticleByName(String name);
	public Client findClientByEmail(String email);
	public List<Order> findOrderByClient(Client client);
	public Article findArticleById(int id);
	
	
}
