package project2.tests;
import static org.easymock.EasyMock.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.service.DatabaseManagerImpl;
import project2.storage.DatabaseStorage;

public class DatabaseManagerEasyMockTest {

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
	public void checkOrderExists() {
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		Order order1 = new Order();
		Order order2 = new Order();
		order1.setClient(client);
		order2.setClient(client);
		order1.setId(1);
		order2.setId(2);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		
		expect(databaseStorage.getAllOrders()).andReturn(orders).anyTimes();
		replay(databaseStorage);
		
		assertTrue(databaseManager.orderExists(1));
		
		EasyMock.verify(databaseStorage);
	}
	
	@Test
	public void checkOrderDoesNotExist() {
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		Order order1 = new Order();
		Order order2 = new Order();
		order1.setClient(client);
		order2.setClient(client);
		order1.setId(1);
		order2.setId(2);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		
		expect(databaseStorage.getAllOrders()).andReturn(orders).anyTimes();
		replay(databaseStorage);
		
		assertFalse(databaseManager.orderExists(3));
	}
	
	@Test
	public void checkAddingMultipleArticlesToOrder() {
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		Order order1 = new Order();
		Order order2 = new Order();
		order1.setClient(client);
		order2.setClient(client);
		order1.setId(1);
		order2.setId(2);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		
		expect(databaseStorage.getAllOrders()).andReturn(orders).anyTimes();
		expect(databaseStorage.getOrder(order1.getId())).andReturn(order1).anyTimes();
		expect(databaseStorage.updateOrder(order1)).andReturn(1);
		replay(databaseStorage);
		
		assertEquals(articles.size(), databaseManager.addMultipleArticlesToOrder(order1.getId(), articles));
		
		EasyMock.verify(databaseStorage);
	}
	
	@Test
	public void checkAddingMultipleArticlesToOrderNotFound() {
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		Order order1 = new Order();
		Order order2 = new Order();
		order1.setClient(client);
		order2.setClient(client);
		order1.setId(1);
		order2.setId(2);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		
		expect(databaseStorage.getAllOrders()).andReturn(orders).anyTimes();
		replay(databaseStorage);
		
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.addMultipleArticlesToOrder(3, articles);
    	}, "Order with this id does not exist");
		
		
		EasyMock.verify(databaseStorage);
	}
	
	
	
	@Test
	public void checkFindClientByEmail()  {
		
		List<Client> clients = new ArrayList<>();	
		Client client1 = new Client(NAME1, SURNAME1, EMAIL1);
		Client client2 = new Client(NAME2, SURNAME2, EMAIL2);
		clients.add(client1);
		clients.add(client2);
		
		
		expect(databaseStorage.getAllClients()).andReturn(clients).anyTimes();
		replay(databaseStorage);
		
		assertAll("clients",
				() -> assertEquals(NAME1, databaseManager.findClientByEmail(EMAIL1).getName()),
	            () -> assertEquals(SURNAME1, databaseManager.findClientByEmail(EMAIL1).getSurname()),
	            () -> assertEquals(EMAIL1, databaseManager.findClientByEmail(EMAIL1).getEmail())
	    );
		
			
		EasyMock.verify(databaseStorage);
		
	}
	
	
	@Test
	public void checkArticleExists() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		article1.setId(1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		article2.setId(2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		articles.add(article2);
		
		expect(databaseStorage.getAllArticles()).andReturn(articles).anyTimes();
		replay(databaseStorage);
		
		assertTrue(databaseManager.articleExists(1));
		
		EasyMock.verify(databaseStorage);
	}
	
	@Test
	public void checkArticleDoesNotExist() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		article1.setId(1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		article2.setId(2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		articles.add(article2);
		
		expect(databaseStorage.getAllArticles()).andReturn(articles).anyTimes();
		replay(databaseStorage);
		
		assertFalse(databaseManager.articleExists(3));
		
		EasyMock.verify(databaseStorage);
	}
	
	@Test
	public void checkDeleteArticleFromOrder() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		article1.setId(1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		article2.setId(2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		articles.add(article2);
		
		Order order = new Order();
		order.setArticles(articles);
		order.setId(1);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		
		expect(databaseStorage.getOrder(order.getId())).andReturn(order).anyTimes();
		expect(databaseStorage.getAllArticles()).andReturn(articles).anyTimes();
		expect(databaseStorage.getAllOrders()).andReturn(orders).anyTimes();
		replay(databaseStorage);
		
		assertEquals(1, databaseManager.deleteArticleFromOrder(order.getId(), article1.getId()));
		
		EasyMock.verify(databaseStorage);
		
	}
	
	@Test
	public void checkDeleteArticleFromOrderArticleNotExists() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		article1.setId(1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		article2.setId(2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		articles.add(article2);
		
		Order order = new Order();
		order.setArticles(articles);
		order.setId(1);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		
		expect(databaseStorage.getOrder(order.getId())).andReturn(order).anyTimes();
		expect(databaseStorage.getAllArticles()).andReturn(articles).anyTimes();
		expect(databaseStorage.getAllOrders()).andReturn(orders).anyTimes();
		replay(databaseStorage);
		
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.deleteArticleFromOrder(order.getId(), 3);
    	}, "Article with this id does not exist");
		
		EasyMock.verify(databaseStorage);
		
	}
	
	@Test
	public void checkDeleteArticleFromOrderOrderNotExists() {
		
		Article article1 = new Article();
		Article article2 = new Article();
		
		article1.setName(ARTICLENAME1);
		article1.setValue(ARTICLEVALUE1);
		article1.setId(1);
		
		article2.setName(ARTICLENAME2);
		article2.setValue(ARTICLEVALUE2);
		article2.setId(2);
		
		List<Article> articles = new ArrayList<>();
		
		articles.add(article1);
		articles.add(article2);
		
		Order order = new Order();
		order.setArticles(articles);
		order.setId(1);
		
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		
		expect(databaseStorage.getOrder(order.getId())).andReturn(order).anyTimes();
		expect(databaseStorage.getAllArticles()).andReturn(articles).anyTimes();
		expect(databaseStorage.getAllOrders()).andReturn(orders).anyTimes();
		replay(databaseStorage);
		
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.deleteArticleFromOrder(3, article1.getId());
    	}, "Order with this id does not exist");
		
		EasyMock.verify(databaseStorage);
		
	}
	
	
}
