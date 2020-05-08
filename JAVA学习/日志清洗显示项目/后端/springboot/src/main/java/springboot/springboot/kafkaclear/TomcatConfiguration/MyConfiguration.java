package springboot.springboot.kafkaclear.TomcatConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*修改tomcat端口，避免和vue端口冲突*/
@Configuration
public class MyConfiguration {
    @Value("${tomcatport:8090}")
    private int port;

    @Bean
    public EmbeddedServletContainerFactory servletContainer(){
        return new TomcatEmbeddedServletContainerFactory(this.port);
    }
}
