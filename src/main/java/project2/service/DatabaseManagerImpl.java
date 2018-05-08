package project2.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.storage.DatabaseStorage;

public class DatabaseManagerImpl implements DatabaseManager {
	
	
	DatabaseStorage databaseStorage;
	

	public DatabaseManagerImpl(DatabaseStorage databaseStorage) {
		
		this.databaseStorage = databaseStorage;
		
	}
	
	public DatabaseManagerImpl() {}


	@Override
	public int addClient(Client client) {

		if(clientExists(client))
			throw new IllegalArgumentException("Client with this email already exists");

		databaseStorage.addClient(client);
		
		return 1;
	}



	@Override
	public int addArticle(Article article) {
		
		if(articleExists(article))
			throw new IllegalArgumentException("Article with this name already exists");

		databaseStorage.addArticle(article);
		
		return 1;
	}


	
	@Override
	public Article findArticleByName(String name) {
	
		
		Optional<Article> article = databaseStorage.getAllArticles().stream().filter(p -> p.getName() == name).findFirst();
		
		if(article.isPresent()) return article.get();
		else throw new IllegalArgumentException("Article with this name does not exist");
	
	}


	@Override
	public Article findArticleById(int id) {
		
		if(id < 0)
			throw new IllegalArgumentException("Id need to be greater or equal 0");
		
		Optional<Article> article = databaseStorage.getAllArticles().stream().filter(p -> p.getId() == id).findFirst();
		
		if(article.isPresent()) return article.get();
		else throw new IllegalArgumentException("Article with this id does not exist");
		
	}

	@Override
	public Client findClientByEmail(String email) {
		

		Optional<Client> client = databaseStorage.getAllClients().stream().filter(p -> p.getEmail() == email).findFirst();
		
		if(client.isPresent()) return client.get();
		else throw new IllegalArgumentException("Client with this email does not exist");
	}


	@Override
	public List<Order> findOrderByClient(Client client) {
		
		List<Order> orders = databaseStorage.getAllOrders().stream().filter(p -> p.getClient().getId() == client.getId()).collect(Collectors.toList());
		if(orders.size() == 0) throw new IllegalArgumentException("No orders found");
		else return orders;
		
	}
	

	public boolean clientExists(Client client) {
		
		List<Client> clients = databaseStorage.getAllClients();
		boolean exist = false;
		
		for(int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getEmail().equals(client.getEmail())) {
				exist = true;
				break;
			}
		}

		
		return exist;
	}
	
	
	public boolean articleExists(Article article) {
		
		List<Article> articles = databaseStorage.getAllArticles();
		boolean exist = false;
		
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).getName() == article.getName()) {
				exist = true;
				break;
			}
		}

		
		return exist;
	}
	
	
	public List<Article> getOrderArticles(Order order) {
		
		return order.getArticles();
		
	}
	
	public boolean orderExists(int id) {
		
		return databaseStorage.getAllOrders().stream().anyMatch(item -> item.getId() == id);
		
	}
	
	public int addMultipleArticlesToOrder(int orderId, List<Article> articles) {
		
		Order order;
		
		if(orderExists(orderId)) order = databaseStorage.getOrder(orderId);
		else throw new IllegalArgumentException("Order with this id does not exist");
		
		order.setArticles(articles);
		
		databaseStorage.updateOrder(order);
		
		
		return articles.size();
	}
	
	public boolean articleExists(int id) {
		
		return databaseStorage.getAllArticles().stream().anyMatch(item -> item.getId() == id);
		
	}
	
	public int deleteArticleFromOrder(int orderId, int articleId) {
		
		if(!orderExists(orderId)) throw new IllegalArgumentException("Order with this id does not exist");
		if(!articleExists(articleId)) throw new IllegalArgumentException("Article with this id does not exist");
		
		Order order = databaseStorage.getOrder(orderId);
		
		order.getArticles().stream().filter(item -> item.getId() != articleId).collect(Collectors.toList());
		
		return 1;

	}






}
