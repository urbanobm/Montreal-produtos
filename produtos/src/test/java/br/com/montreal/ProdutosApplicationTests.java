package br.com.montreal;

import org.junit.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ProdutosApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ProdutosConfiguracao.class)
                .build().run(args);
    }
}
