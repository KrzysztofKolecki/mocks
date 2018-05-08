package project2.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.mocks.DatabaseStorageMock;
import project2.service.DatabaseManager;
import project2.service.DatabaseManagerImpl;
import project2.storage.DatabaseStorage;

public class DatabaseManagerMockTest {
	
	DatabaseStorage databaseStorage = new DatabaseStorageMock();
	DatabaseManager databaseManager = new DatabaseManagerImpl(databaseStorage);
	
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
		
		databaseManager.addClient(client);
		
		assertAll("client",
				() -> assertEquals(NAME1, databaseStorage.getAllClients().get(0).getName()),
	            () -> assertEquals(SURNAME1, databaseStorage.getAllClients().get(0).getSurname()),
	            () -> assertEquals(EMAIL1, databaseStorage.getAllClients().get(0).getEmail())
	    );
	}
	
	@Test
	public void checkAddingClientWithNullArgument(){
		

		assertThrows(NullPointerException.class, () -> {
	            		databaseManager.addClient(null);
	            	});
		
	}
	
	@Test
	public void checkAddingClientThatAlreadyExists(){
		
		Client client = new Client(NAME1, SURNAME1, EMAIL1);
		
		databaseManager.addClient(client);
		
		assertThrows(IllegalArgumentException.class, () -> {
	            		databaseManager.addClient(client);
	            	}, "Client with this email already exists");
		
	}
	
			
	@Test
	public void checkAddingArticle(){
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		
		databaseManager.addArticle(article);
		
		assertAll("article",
				() -> assertEquals(ARTICLENAME1, databaseStorage.getAllArticles().get(0).getName()),
	            () -> assertEquals(ARTICLEVALUE1, databaseStorage.getAllArticles().get(0).getValue())
	    );
		
	}
	
	@Test
	public void checkAddingArticleThatAlreadyExists(){
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		
		databaseManager.addArticle(article);
		
		assertThrows(IllegalArgumentException.class, () -> {
						databaseManager.addArticle(article);
	            	}, "Article with this name already exists");
		
	}
	
	@Test
	public void checkFindingArticleByName() {
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		
		databaseManager.addArticle(article);
		
		assertEquals(article, databaseManager.findArticleByName(ARTICLENAME1));

		
	}
	
	@Test
	public void checkFindingArticleByNameNotFound() {
		
	
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.findArticleByName(ARTICLENAME2);
    	}, "Article with this name does not exist");

		
	}
	
	@Test
	public void checkFindingArticleById() {
		
		Article article = new Article();
		article.setName(ARTICLENAME1);
		article.setValue(ARTICLEVALUE1);
		
		databaseManager.addArticle(article);
		
		int articleId =  databaseStorage.getAllArticles().get(0).getId();
		
		assertEquals(article, databaseManager.findArticleById(articleId));

		
	}
	
	@Test
	public void checkFindingArticleByIdNotFound() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.findArticleById(0);
    	}, "Article with this id does not exist");
		
	}
	
	@Test
	public void checkFindingArticleByIdLessThanZero() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			databaseManager.findArticleById(-1);
    	}, "Id need to be greater or equal 0");
		
	}
	
	
}
