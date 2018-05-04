package project2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import project2.domain.Client;
import project2.service.DatabaseManagerImpl;

//import org.junit.jupiter.api.*;

public class DatabaseManagerTest {
	

	DatabaseManagerImpl databaseManager = new DatabaseManagerImpl();
	
	@Test
	public void checkAddingClient(){
		
		Client client = new Client("Krzysztof", "Kolecki", "kkolecki@sigma.ug.edu.pl");
		
		assertEquals(1, databaseManager.addClient(client));
	
	}
	

}
