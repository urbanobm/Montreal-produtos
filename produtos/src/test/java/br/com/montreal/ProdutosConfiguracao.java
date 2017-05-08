package br.com.montreal;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EntityScan("br.com.montreal.entity")
@EnableJpaRepositories("br.com.montreal.repository")
@ComponentScan({"br.com.montreal"})
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class ProdutosConfiguracao {

}
