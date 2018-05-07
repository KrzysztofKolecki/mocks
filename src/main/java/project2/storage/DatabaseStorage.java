package project2.storage;

import java.util.List;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;

public interface DatabaseStorage {
	
	public int addClient(Client client);
	public int addOrder(Order order);
	public int addArticle(Article article);
	public int addArticleToOrder(Order order, Article article);
	public Client getClient(int id);
	public Order getOrder(int id);
	public Article getArticle(int id);
	public int updateClient(Client client);
	public int updateOrder(Order order);
	public int updateArticle(Article article);
	public int deleteClient(int id);
	public int deleteOrder(int id);
	public int deleteArticle(int id);
	public List<Client> getAllClients();
	public List<Order> getAllOrders();
	public List<Article> getAllArticles();
	public void deleteAllClients();
	public void deleteAllOrders();
	public void deleteAllArticles();
	public void deleteAllOrderArticle();
	
}
