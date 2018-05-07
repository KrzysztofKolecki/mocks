package project2.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import project2.domain.Article;
import project2.domain.Client;
import project2.domain.Order;
import project2.storage.DatabaseStorage;

public class DatabaseManagerImpl implements DatabaseManager {
	
	
	DatabaseStorage databaseStorage;
	

	public DatabaseManagerImpl(DatabaseStorage databaseStorage) {
		
		this.databaseStorage = databaseStorage;
		
	}


	@Override
	public int addClient(Client client) {
		
		if(client == null)
			throw new IllegalArgumentException("Null argument");
		
		if(clientExists(client))
			throw new IllegalArgumentException("Client already exists");

		databaseStorage.addClient(client);
		
		return 1;
	}



	@Override
	public int addArticle(Article article) {
		
		if(article == null)
			throw new IllegalArgumentException("Null argument");
		
		if(articleExists(article))
			throw new IllegalArgumentException("Client already exists");

		databaseStorage.addArticle(article);
		
		return 1;
	}


	


	@Override
	public Article findArticleByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Client findClientById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Order findOrderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Article findArticleById(int id) {
		// TODO Auto-generated method stub
		return null;
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
	
	

	private boolean clientExists(Client client) {
		
		List<Client> clients = databaseStorage.getAllClients();
		boolean exist = false;
		
		for(int i = 0; i < clients.size(); i++) {
			if(clients.get(i).equals(client)) exist = true;
		}

		
		return exist;
	}
	
	
	private boolean articleExists(Article article) {
		
		List<Article> articles = databaseStorage.getAllArticles();
		boolean exist = false;
		
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).equals(article)) exist = true;
		}

		
		return exist;
		
		
	}



}
