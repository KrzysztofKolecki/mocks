package project2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.service.DatabaseManager;
import project2.service.DatabaseManagerImpl;


public class DatabaseManagerTest {
	
	DatabaseManagerImpl databaseManager = new DatabaseManagerImpl();
	
	String NAME1 = "Krzysztof";
	String NAME2 = "Bartek";
	String SURNAME1 = "Kolecki";
	String SURNAME2 = "Brzoza";
	String EMAIL1 = "kkolecki@sigma.ug.edu.pl";
	String EMAIL2 = "brzoza@sigma.ug.edu.pl";
	String ARTICLENAME1 = "Patagonia";
	Double ARTICLEVALUE1 = 199.99;
	
	
	@AfterEach
    void clearDatabase() {
		databaseManager.deleteAllOrders();
		databaseManager.deleteAllClients();
		databaseManager.deleteAllArticles();	
    }
	
	@Test
	public void checkAddingClient(){
			
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		
		databaseManager.addClient(client);
		
		assertAll("client",
				() -> assertEquals(NAME1, databaseManager.getAllClients().get(0).getName()),
	            () -> assertEquals(SURNAME1, databaseManager.getAllClients().get(0).getSurname()),
	            () -> assertEquals(EMAIL1, databaseManager.getAllClients().get(0).getEmail())
	    );
	
	}
	
	@Test
	public void checkAddingOrder(){
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		databaseManager.addClient(client);
		int clientId = databaseManager.getAllClients().get(0).getId();
		client = databaseManager.getClient(clientId);
		Order order = new Order();
		order.setClient(client);
		
		assertEquals(1, databaseManager.addOrder(order));
	
	}
	
	@Test
	public void checkAddingArticle(){
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		
		databaseManager.addArticle(article);
		
		assertAll("article",
				() -> assertEquals(ARTICLENAME1, databaseManager.getAllArticles().get(0).getName()),
	            () -> assertEquals(ARTICLEVALUE1, databaseManager.getAllArticles().get(0).getValue())
	    );
		
	}
	
	@Test
	@Disabled
	public void checkAddingArticleToOrder(){
		
		Article article = new Article();
		article.setName("Nike air");
		article.setValue(99.99);
		
		assertEquals(1, databaseManager.addArticle(article));
	
	}
	
	@Test
	public void checkGettingClient(){

		Client client = new Client(NAME2, SURNAME2, EMAIL2);
		
		databaseManager.addClient(client);
		
		int id = databaseManager.getAllClients().get(0).getId();
						
		assertAll("client",
				() -> assertEquals(NAME2, databaseManager.getClient(id).getName()),
	            () -> assertEquals(SURNAME2, databaseManager.getClient(id).getSurname()),
	            () -> assertEquals(EMAIL2, databaseManager.getClient(id).getEmail())
	    );
	
	}
	
	@Test
	public void checkGettingOrder(){
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		databaseManager.addClient(client);
		int clientId = databaseManager.getAllClients().get(0).getId();
		client = databaseManager.getClient(clientId);
		Order order = new Order();
		order.setClient(client);
		
		databaseManager.addOrder(order);
	
		int id = databaseManager.getAllOrders().get(0).getId();
						
		assertAll("client",
				() -> assertEquals(id, databaseManager.getOrder(id).getId()),
	            () -> assertEquals(clientId, databaseManager.getOrder(id).getClient().getId())
	    );
	
	}
	
	@Test
	public void deleteAllClients(){

		Client client1 = new Client(NAME1, SURNAME1, EMAIL1);
		Client client2 = new Client(NAME2, SURNAME2, EMAIL2);
		
		databaseManager.addClient(client1);
		databaseManager.addClient(client2);
		
		databaseManager.deleteAllClients();
				
		assertEquals(0, databaseManager.getAllClients().size());
	
	}

}
