package ae.teletronics;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by kristian on 3/28/16.
 */
public class ExternalAuthTest {

    @Test
    public void testParseInput() throws Exception{
        final ExternalAuth mock = Mockito.mock(ExternalAuth.class);
        Mockito.doCallRealMethod().when(mock).parseInput(Matchers.anyString());

        assertEquals(CommandType.UNKNOWN, mock.parseInput("").commandType);
        assertEquals(CommandType.UNKNOWN, mock.parseInput("test").commandType);
        assertEquals(CommandType.UNKNOWN, mock.parseInput("test:test:test:test").commandType);
        assertEquals(CommandType.UNKNOWN, mock.parseInput("setpass:test").commandType);

        assertEquals(CommandType.SET_PASS, mock.parseInput("setpass:username:server:password").commandType);
        assertEquals(CommandType.TRY_REGISTER, mock.parseInput("tryregister:username:server:password").commandType);
        assertEquals(CommandType.REMOVE_USER, mock.parseInput("removeuser:username:server").commandType);
        assertEquals(CommandType.REMOVE_SAFELY, mock.parseInput("removeuser3:username:server:password").commandType);
    }

}