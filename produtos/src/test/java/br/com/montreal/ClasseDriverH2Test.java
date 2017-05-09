package br.com.montreal;

import org.junit.Assert;
import org.junit.Test;

public class ClasseDriverH2Test {

	@Test
	public void testClasseDriver() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			Assert.fail("Erro classe não encontrada: " + e.getMessage());
		}
	}
}
