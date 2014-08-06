package jczhackdag.fotodump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//The @ComponentScan annotation tells Spring to search recursively through the current package and
// its children for classes marked directly or indirectly with Spring’s @Component annotation.
// This directive ensures that Spring finds and registers the FotoController,
// because it is marked with @RestController, which in turn is a kind of @Component annotation.
@ComponentScan
//The @EnableAutoConfiguration annotation switches on reasonable default behaviors based on the content of your classpath.
// For example, because the application depends on the embeddable version of Tomcat (tomcat-embed-core.jar), a Tomcat server
// is set up and configured with reasonable defaults on your behalf. And because the application also depends on Spring
// MVC (spring-webmvc.jar), a Spring MVC DispatcherServlet is configured and registered for you — no web.xml necessary!
// Auto-configuration is a powerful, flexible mechanism.
@EnableAutoConfiguration
public class Application {

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}