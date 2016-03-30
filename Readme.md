# EjabberdExternalAuthJava

This framework helps you building external usermanagement script for ejabberd.
- https://www.ejabberd.im/extauth
- https://www.ejabberd.im/files/doc/dev.html#htoc9

## Getting started

1. Create a maven project in your favorite IDE.
2. Add the dependency
```
        <dependency>
            <groupId>ae.teletronics</groupId>
            <artifactId>ejabberd-external-auth-java</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```
3. Create a class that extends ```ae.teletronics.ExternalAuth```
```java
import ae.teletronics.ExternalAuth;

public class OptimistAuth extends ExternalAuth {

    @Override
    public boolean authenticate(String user, String server, String password) {
        return true;
    }

    @Override
    public boolean exists(String user, String server) {
        return true;
    }

    @Override
    public boolean setPassword(String user, String server, String password) {
        return true;
    }

    @Override
    public boolean register(String user, String server, String password) {
        return true;
    }

    @Override
    public boolean remove(String user, String server) {
        return true;
    }

    @Override
    public boolean removeSafely(String user, String server, String password) {
        return true;
    }
}
```
4. Assemble you application as a executable jar with ae.teletronics.Application as the mainClass. This can be done by adding the following to your pom.xml
```
<build>
    <plugins>
        <plugin>
            <artifactId>maven-assembly-plugin</artifactId>
            <configuration>
                <archive>
                    <manifest>
                        <mainClass>ae.teletronics.Application</mainClass>
                    </manifest>
                </archive>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
            <executions>
                <execution>
                    <id>make-assembly</id>
                    <phase>package</phase>
                    <goals>
                        <goal>single</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
5. Make your implementation
6. Add this to your ejabberd configuration
```
auth_method:
  - external
extauth_program: "java -jar /authentication/OptimistAuth-1.0-SNAPSHOT-jar-with-dependencies.jar"
extauth_cache: 0
extauth_instances: 10
```
