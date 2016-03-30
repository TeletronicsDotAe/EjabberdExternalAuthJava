package ae.teletronics;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * Created by kristian on 3/28/16.
 */
public class Application {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        Application application = new Application();

        ExternalAuth  externalAuth = application.getImplementation();
        externalAuth.setup();
        externalAuth.readInput();
    }

    ExternalAuth getImplementation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        final Reflections reflections = new Reflections();
        final Set<Class<? extends ExternalAuth>> externalAuthImplementations = reflections.getSubTypesOf(ExternalAuth.class);
        final Optional<Class<? extends ExternalAuth>> implementationOptional = externalAuthImplementations.stream().findFirst();

        final Class<? extends ExternalAuth> implementationClass = implementationOptional.orElseThrow(() -> {
            return new ClassNotFoundException("There was no implementation of " + ExternalAuth.class.getName() + " supplied on classpath");
        });

        return implementationClass.newInstance();
    }



}
