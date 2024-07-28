package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class Cadastro {

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createPoint() {
		
		//open= função do selenide, inicia a operação, estamos usando uma ferramenta parecida com cypress
		//que torna a automação mais "fluida"
		//$ = usado para buscar, shouldHave = faz a validação do texto. Usando esses 2 evitamos o uso dos codigos
		//que tinham antes, WebElement, WebDriver, assertEquals(caso precise, olhar a class Selenium)
		//selenide não precisa do close

		open("https://petlov.vercel.app/signup");

		$("h1").shouldHave(text("Cadastro de ponto de doação"));

		$("input[placeholder='Nome do ponto de doação']").setValue("Papito Point");
		
		$("input[name=email]").setValue("papito@point.net");

		$("input[name=cep]").setValue("21510105");

		$("input[value='Buscar CEP']").click();
		
		$("input[name=addressNumber]").setValue("1000");
		
		$("input[name=addressDetails]").setValue("Ao lado da padaria");

		$(By.xpath("//span[text()=\"Cachorros\"]/..")).click();

		$(".button-register").click(); //buscar por classe do jeito que estava, precisa do .

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("#success-page p").shouldHave(text(target));

	}
}
