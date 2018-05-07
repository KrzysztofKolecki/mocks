package project2.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.mocks.DatabaseStorageMock;
import project2.service.DatabaseManagerImpl;
import project2.storage.DatabaseStorage;

public class DatabaseManagerMockTest {
	
	DatabaseStorage databaseStorage = new DatabaseStorageMock();
	
	DatabaseManagerImpl databaseManager = new DatabaseManagerImpl(databaseStorage);
	
	String NAME1 = "Krzysztof";
	String NAME2 = "Bartek";
	String SURNAME1 = "Kolecki";
	String SURNAME2 = "Brzoza";
	String EMAIL1 = "kkolecki@sigma.ug.edu.pl";
	String EMAIL2 = "brzoza@sigma.ug.edu.pl";
	String ARTICLENAME1 = "Patagonia";
	Double ARTICLEVALUE1 = 199.99;
	String ARTICLENAME2 = "Nike Air";
	Double ARTICLEVALUE2 = 99.99;
	
	
	
	@AfterEach
    void clearDatabase() {
		databaseStorage.deleteAllOrderArticle();
		databaseStorage.deleteAllOrders();
		databaseStorage.deleteAllClients();
		databaseStorage.deleteAllArticles();	
    }
	
	@Test
	public void checkAddingClient(){
			
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		
		databaseStorage.addClient(client);
		
		assertAll("client",
				() -> assertEquals(NAME1, databaseStorage.getAllClients().get(0).getName()),
	            () -> assertEquals(SURNAME1, databaseStorage.getAllClients().get(0).getSurname()),
	            () -> assertEquals(EMAIL1, databaseStorage.getAllClients().get(0).getEmail())
	    );
	
	}
	
	@Test
	public void checkAddingOrder(){
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		databaseStorage.addClient(client);
		int clientId = databaseStorage.getAllClients().get(0).getId();
		client = databaseStorage.getClient(clientId);
		Order order = new Order();
		order.setClient(client);
		
		assertEquals(1, databaseStorage.addOrder(order));
	
	}
	
	@Test
	public void checkAddingArticle(){
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		
		databaseStorage.addArticle(article);
		
		assertAll("article",
				() -> assertEquals(ARTICLENAME1, databaseStorage.getAllArticles().get(0).getName()),
	            () -> assertEquals(ARTICLEVALUE1, databaseStorage.getAllArticles().get(0).getValue())
	    );
		
	}
	
	@Test
	public void checkAddingArticleToOrder(){
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		databaseStorage.addArticle(article);
		article = databaseStorage.getAllArticles().get(0);
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		databaseStorage.addClient(client);
		int clientId = databaseStorage.getAllClients().get(0).getId();
		client = databaseStorage.getClient(clientId);
		
		Order order = new Order();
		order.setClient(client);
		databaseStorage.addOrder(order);
		order = databaseStorage.getAllOrders().get(0);
		
		
		assertEquals(1, databaseStorage.addArticleToOrder(order, article));
	
	}
	
	@Test
	public void checkGettingClient(){

		Client client = new Client(NAME2, SURNAME2, EMAIL2);
		
		databaseStorage.addClient(client);
		
		int id = databaseStorage.getAllClients().get(0).getId();
						
		assertAll("client",
				() -> assertEquals(NAME2, databaseStorage.getClient(id).getName()),
	            () -> assertEquals(SURNAME2, databaseStorage.getClient(id).getSurname()),
	            () -> assertEquals(EMAIL2, databaseStorage.getClient(id).getEmail())
	    );
	
	}
	
	@Test
	public void checkGettingOrder(){
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		databaseStorage.addClient(client);
		int clientId = databaseStorage.getAllClients().get(0).getId();
		client = databaseStorage.getClient(clientId);
		Order order = new Order();
		order.setClient(client);
		
		databaseStorage.addOrder(order);
	
		int id = databaseStorage.getAllOrders().get(0).getId();
						
		assertAll("client",
				() -> assertEquals(id, databaseStorage.getOrder(id).getId()),
	            () -> assertEquals(clientId, databaseStorage.getOrder(id).getClient().getId())
	    );

	}
	
	@Test
	public void checkGettingArticle(){
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		
		databaseStorage.addArticle(article);
		
		int id = databaseStorage.getAllArticles().get(0).getId();
		
		assertAll("article",
				() -> assertEquals(ARTICLENAME1, databaseStorage.getArticle(id).getName()),
	            () -> assertEquals(ARTICLEVALUE1, databaseStorage.getArticle(id).getValue())
	    );
	
	}
	
	@Test
	public void deleteAllClients(){

		Client client1 = new Client(NAME1, SURNAME1, EMAIL1);
		Client client2 = new Client(NAME2, SURNAME2, EMAIL2);
		
		databaseStorage.addClient(client1);
		databaseStorage.addClient(client2);
		
		databaseStorage.deleteAllClients();
				
		assertEquals(0, databaseStorage.getAllClients().size());
	
	}
}
