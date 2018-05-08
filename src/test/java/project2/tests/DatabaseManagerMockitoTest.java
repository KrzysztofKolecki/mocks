package project2.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import project2.domain.Client;
import project2.mocks.DatabaseStorageMock;
import project2.service.DatabaseManager;
import project2.service.DatabaseManagerImpl;
import project2.storage.DatabaseStorage;

public class DatabaseManagerMockitoTest {

	DatabaseStorage databaseStorage = mock(DatabaseStorage.class);
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
	
}
