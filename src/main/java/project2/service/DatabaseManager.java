package project2.service;

import java.util.List;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;

public interface DatabaseManager {
	
	public int addClient(Client client);
	public int addArticle(Article article);
	public Article findArticleByName(String name);
	public Client findClientById(int id);
	public Order findOrderById(int id);
	public Article findArticleById(int id);
	public int updateClient(Client client);
	public int updateOrder(Order order);
	public int updateArticle(Article article);
	public int deleteClient(int id);
	public int deleteOrder(int id);
	public int deleteArticle(int id);
	
}
