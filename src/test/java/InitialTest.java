import com.inst.hibernate.domain.Account;
import com.inst.hibernate.domain.Client;
import com.inst.hibernate.initializer.Initializer;
import com.inst.hibernate.initializer.impl.InitializerImpl;
import com.inst.hibernate.util.nosql.MongoManager;
import com.mongodb.DB;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Dmitry.
 */
public class InitialTest {

    private Initializer initializer;
    private boolean flag = false;
    private static final String DB_NAME = "test";

    @Test
    public void startInitialization() {
        MongoManager.getInstance().getMongoClient();
        DB db = MongoManager.getInstance().getDB(DB_NAME);
        //check database
        assertEquals(DB_NAME, db.getName());

        //check exists collection for domain classes
        assertNotNull(db.getCollection(Account.class.getSimpleName()) != null);
        assertNotNull(db.getCollection(Client.class.getSimpleName()) != null);
    }

    @Test
    public void cleanData() {
        //clear all data before testing application
        List<Account> accounts = getInitializer().getAllAccounts();
        accounts.forEach(account -> getInitializer().deleteAccount(account));
        assertEquals(0, getInitializer().getAllAccounts().size());

        List<Client> clients = getInitializer().getAllClients();
        clients.forEach(client -> getInitializer().deleteClient(client));
        assertEquals(0, getInitializer().getAllClients().size());

        getInitializer().switchDB(!flag);

        //now clean data for Mongo
        accounts = getInitializer().getAllAccounts();
        accounts.forEach(account -> getInitializer().deleteAccount(account));
        assertEquals(0, getInitializer().getAllAccounts().size());

        clients = getInitializer().getAllClients();
        clients.forEach(client -> getInitializer().deleteClient(client));
        assertEquals(0, getInitializer().getAllClients().size());

        //and again switch database for next tests
        getInitializer().switchDB(!flag);
    }

    @Test
    public void checkServicesAndWorkWithPostgres() {
        Client client = new Client();
        client.setName("TestUser");
        getInitializer().addClient(client);

        List<Client> clientList = getInitializer().getAllClients().stream()
                .filter(obj -> obj.getName().equals(client.getName()))
                .collect(Collectors.toList());

        int countUsers = clientList.size();
        assertEquals(1, countUsers);

        Client updatedClient = clientList.get(0);
        updatedClient.setName("NewNameUser");
        getInitializer().updateClient(client, updatedClient);

        clientList = getInitializer().getAllClients();
        assertEquals("NewNameUser", clientList.get(0).getName());

        getInitializer().deleteClient(updatedClient);
        assertEquals(0, getInitializer().getAllClients().size());

    }

    @Test
    public void checkServicesAndWorkWithMongo() {
        //check work switch database
        getInitializer().switchDB(!flag);


    }

    private Initializer getInitializer() {
        if(initializer == null) {
            initializer = new InitializerImpl();
            initializer.init();
        }
        return initializer;
    }
}
