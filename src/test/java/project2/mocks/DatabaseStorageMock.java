package project2.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.storage.DatabaseStorage;

public class DatabaseStorageMock implements DatabaseStorage {
	
	private List<Client> clients = new ArrayList<>();
	private List<Order> orders = new ArrayList<>();
	private List<Article> articles = new ArrayList<>();
	
	@Override
	public int addClient(Client client) {
		
		client.setId(clients.size());
		clients.add(client);
		
		
		return 1;
	}

	@Override
	public int addOrder(Order order) {
		
		order.setId(orders.size());
		orders.add(order);
		
		return 1;
		
	}

	@Override
	public int addArticle(Article article) {
		
		article.setId(articles.size());
		articles.add(article);
		
		return 1;
	}

	@Override
	public int addArticleToOrder(Order order, Article article) {
		
		List<Article> a = order.getArticles();
		a.add(article);
		order.setArticles(a);
		
		return 1;
	}

	@Override
	public Client getClient(int id) {
		
		Optional<Client> c = clients.stream().filter(p -> p.getId() == id).findFirst();
		
		return c.get();
		
	}

	@Override
	public Order getOrder(int id) {
		
		Optional<Order> c = orders.stream().filter(p -> p.getId() == id).findFirst();
		
		return c.get();
	}

	@Override
	public Article getArticle(int id) {
		
		Optional<Article> c = articles.stream().filter(p -> p.getId() == id).findFirst();
		
		return c.get();
	}

	@Override
	public int updateClient(Client client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateArticle(Article article) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClient(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOrder(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArticle(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Client> getAllClients() {
		
		return clients;
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orders;
	}

	@Override
	public List<Article> getAllArticles() {

		return articles;
	}

	@Override
	public void deleteAllClients() {
		
		clients.clear();
		
	}

	@Override
	public void deleteAllOrders() {
		
		orders.clear();
		
	}

	@Override
	public void deleteAllArticles() {
		
		articles.clear();
		
	}

	@Override
	public void deleteAllOrderArticle() {
		// TODO Auto-generated method stub
		
	}

}
