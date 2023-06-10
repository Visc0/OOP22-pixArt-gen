package it.unibo.pixArt.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.user.manager.UserManager;
import it.unibo.pixArt.model.user.manager.UserManagerImpl;
import it.unibo.pixArt.model.user.storage.UserDataStorageImpl;

/** The Test class of the UserManagerImpl class.
 */
public final class UserManagerImplTest {

    private static final char FILESEPARATOR = File.separatorChar;
    private static final String USERDATAPATH = System.getProperty("user.home") + FILESEPARATOR 
                                                + "userData" + FILESEPARATOR + "users.json"; 

    private UserManager userManager;
    private final User user1 = new UserImpl("luigiBianchi", "luigi001",
                                System.getProperty("user.dir") + File.separator + "Downloads");
    private final User user2 = new UserImpl("marcoRossi", "marco002",
                                System.getProperty("user.dir") + File.separator + "Downloads");

    /** Creates a new User Manager.
     */
    public void createUserManager() {
        this.userManager = new UserManagerImpl(new UserDataStorageImpl());
    }

    /**
     * @throws IOException
     * Test the login with empty file
     */
    @Test
    public void loginWithEmptyFile() throws IOException {
        createUserManager();
        assertEquals(Optional.empty(), this.userManager.login("giovanni", "giovanni000"));
        this.deleteFile();
    }

    /** 
     * @throws IOException
     * Test the login
     */
    @Test 
    public void login() throws IOException {
        createUserManager();
        this.userManager.register(user1.getName(), user1.getPassword(), user1.getPathToFile());
        assertEquals(user1.getName(), this.userManager.login(user1.getName(), user1.getPassword()).get().getName());
        this.deleteFile();
    }

    /** 
     * @throws IOException
     * Test register an existing user
     */
    @Test
    public void registerExistentUser() throws IOException {
        createUserManager();
        this.userManager.register(user2.getName(), user2.getPassword(), user2.getPathToFile());
        assertEquals(user2.getName(), this.userManager.login(user2.getName(), user2.getPassword()).get().getName());
        assertEquals(Optional.empty(), this.userManager.register(user2.getName(), user2.getPassword(), user2.getPathToFile()));
        this.deleteFile();
    }

    private void deleteFile() throws IOException {
        Files.deleteIfExists(Path.of(USERDATAPATH));
    }

}
