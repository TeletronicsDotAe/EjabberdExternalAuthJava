package ae.teletronics;

/**
 * Created by kristian on 3/28/16.
 */
public interface UserManagement {

    /**
     *
     * Corresponds to ejabberd `auth` operation.
     *
     * @param user Username of the user. Examples: test, testuser, kristian
     * @param server Server on which the user eixsts. Example: chat.teletronics.ae, chat.github.com
     * @param password Password for the user.
     */
    public boolean authenticate(String user, String server, String password);

    /**
     *
     * Corresponds to ejabberd `isuser` operation.
     *
     * @param user Username of the user. Examples: test, testuser, kristian
     * @param server Server on which the user eixsts. Example: chat.teletronics.ae, chat.github.com
     */
    public boolean exists(String user, String server);

    /**
     *
     * Corresponds to ejabberd `setpass` operation.
     *
     * @param user Username of the user. Examples: test, testuser, kristian
     * @param server Server on which the user eixsts. Example: chat.teletronics.ae, chat.github.com
     * @param password Password for the user.
     */
    public boolean setPassword(String user, String server, String password);

    /**
     *
     * Corresponds to `tryregister` operation.
     *
     * @param user Username of the user. Examples: test, testuser, kristian
     * @param server Server on which the user eixsts. Example: chat.teletronics.ae, chat.github.com
     * @param password Password for the user.
     */
    public boolean register(String user, String server, String password);

    /**
     * Corresponds to `removeuser` operation.
     * @param user Username of the user. Examples: test, testuser, kristian
     * @param server Server on which the user eixsts. Example: chat.teletronics.ae, chat.github.com
     */
    public boolean remove(String user, String server);

    /**
     * Corresponds to `removeuser3` operation.
     * @param user Username of the user. Examples: test, testuser, kristian
     * @param server Server on which the user eixsts. Example: chat.teletronics.ae, chat.github.com
     * @param password Password for the user.
     */
    public boolean removeSafely(String user, String server, String password);
}
