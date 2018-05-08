package project2.tests;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.service.DatabaseManagerImpl;
import project2.storage.DatabaseStorage;

public class DatabaseManagerMockitoTest {

	DatabaseStorage databaseStorage = mock(DatabaseStorage.class);
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
	
	@Test
	public void checkFindClientByEmail()  {
		
		List<Client> clients = new ArrayList<>();	
		Client client1 = new Client(NAME1, SURNAME1, EMAIL1);
		Client client2 = new Client(NAME2, SURNAME2, EMAIL2);
		clients.add(client1);
		clients.add(client2);
		
		when(databaseStorage.getAllClients()).thenReturn(clients);
		
		assertAll("clients",
				() -> assertEquals(NAME1, databaseManager.findClientByEmail(EMAIL1).getName()),
	            () -> assertEquals(SURNAME1, databaseManager.findClientByEmail(EMAIL1).getSurname()),
	            () -> assertEquals(EMAIL1, databaseManager.findClientByEmail(EMAIL1).getEmail())
	    );
		
	}
	
	@Test
	public void checkFindClientByEmailNotFound()  {
		
		List<Client> clients = new ArrayList<>();	
		Client client1 = new Client(NAME1, SURNAME1, EMAIL1);
		clients.add(client1);
		
		when(databaseStorage.getAllClients()).thenReturn(clients);
		
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.findClientByEmail(EMAIL2);
    	}, "Client with this email does not exist");
		
	}
	
	@Test
	public void checkFindOrderByClient()  {
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		Order order1 = new Order();
		Order order2 = new Order();
		order1.setClient(client);
		order2.setClient(client);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		
		when(databaseStorage.getAllOrders()).thenReturn(orders);
		
		assertEquals(2, databaseManager.findOrderByClient(client).size());

		
	}
	
	
	@Test
	public void checkFindOrderByClientNotFound()  {
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		client.setId(0);
		Order order1 = new Order();
		Order order2 = new Order();
		order1.setClient(client);
		order2.setClient(client);
		
		Client client2 = new Client(NAME2, SURNAME2, EMAIL2);
		client2.setId(1);

		
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		
		when(databaseStorage.getAllOrders()).thenReturn(orders);
		
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.findOrderByClient(client2);
    	}, "No orders found");
		
	}
	
	
	@Test
	public void checkClientExists()  {
		
		Client client1 = new Client(NAME1, SURNAME1, EMAIL1);
		Client client2 = new Client(NAME2, SURNAME2, EMAIL2);
		
		List<Client> clients = new ArrayList<>();
		clients.add(client1);
		clients.add(client2);
		
		when(databaseStorage.getAllClients()).thenReturn(clients);
		
		assertTrue(databaseManager.clientExists(client1));
		
	}
	
	@Test
	public void checkClientDoesNotExist()  {
		
		Client client1 = new Client(NAME1, SURNAME1, EMAIL1);
		Client client2 = new Client(NAME2, SURNAME2, EMAIL2);
		
		List<Client> clients = new ArrayList<>();
		clients.add(client1);
		
		when(databaseStorage.getAllClients()).thenReturn(clients);
		
		assertFalse(databaseManager.clientExists(client2));
		
	}
	
	@Test
	public void checkArticleExists() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		articles.add(article2);
		
		when(databaseStorage.getAllArticles()).thenReturn(articles);
		
		assertTrue(databaseManager.articleExists(article1));
		
	}
	
	@Test
	public void checkArticleDoesNotExist() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);

		
		when(databaseStorage.getAllArticles()).thenReturn(articles);
		
		assertFalse(databaseManager.articleExists(article2));
		
	}
	
	@Test
	public void checkGetOrderArticles() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		articles.add(article2);
		
		Order order = new Order();
		order.setArticles(articles);

		when(databaseStorage.getOrder(anyInt())).thenReturn(order);
		
		assertNotEquals(0, databaseManager.getOrderArticles(order).size());
		
	}
	
	@Test
	public void checkGetOrderArticlesEmpty() {
		
		
		Order order = new Order();

		when(databaseStorage.getOrder(anyInt())).thenReturn(order);
		
		assertEquals(0, databaseManager.getOrderArticles(order).size());
		
	}
	
	
}
