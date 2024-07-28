package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class Slogan {

	@Test
	@DisplayName("Deve exibir o slogan do site")
	void sloganTest() {
		WebDriver driver = new ChromeDriver(); //conexão, variavel padrão do selenium
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); //tempo implicito de 2s

		driver.get("https://petlov.vercel.app"); //chamar a função get, para entrar no site

		WebElement title = driver.findElement(By.cssSelector("h1"));
		//findE- busca de elemento atraves da tag h1(texto)
		
		//crio um obj usando o driver como parametro e o tempo de duração de 2
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(d -> title.isDisplayed());

		//until = aguarda até o elemento title esteja em display, sem ela não funciona no selenium
		//coloco a variavel wait ali para que seja usado tanto o driver quanto o tempo

		assertEquals("Conectando corações, mudando vidas!", title.getText(), "Verificando o Slogan");
		//verifica se o texto do elemento title é o mesmo que "conecta..." e a ultima mensagem é de verificação 

		driver.close(); //fecha o navegador
	}
}
