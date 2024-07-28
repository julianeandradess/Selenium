package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

//a diferença para as 2 classes é que a cadastro é pra teste e a PontoDoacao não será usada para definir testes
//e sim para definir a modelagem de dados do teste automatizado
class PontoDoacao{
	String nome;
	String email;
	String cep;
	Integer numero;
	String complemento;
	String pets;

	public PontoDoacao(String nome, String email, String cep, Integer numero, String complemento, String pets) {
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.pets = pets;
	}
	
}

class MelhorandoCadastro {

	private void submeterFormulario(PontoDoacao ponto){
		//Ação
		$("h1").shouldHave(text("Cadastro de ponto de doação"));
		$("input[placeholder='Nome do ponto de doação']").setValue(ponto.nome);
		$("input[name=email]").setValue(ponto.email);
		$("input[name=cep]").setValue(ponto.cep);
		$("input[value='Buscar CEP']").click();
		$("input[name=addressNumber]").setValue(ponto.numero.toString());
		$("input[name=addressDetails]").setValue(ponto.complemento);
		$(By.xpath("//span[text()=\"" + ponto.pets + "\"]/..")).click();
		$(".button-register").click(); //buscar por classe do jeito que estava, precisa do .
	}
	
	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createPoint() {
		//open= função do selenide, inicia a operação, estamos usando uma ferramenta parecida com cypress
		//que torna a automação mais "fluida"
		//$ = usado para buscar, shouldHave = faz a validação do texto. Usando esses 2 evitamos o uso dos codigos
		//que tinham antes, WebElement, WebDriver, assertEquals(caso precise, olhar a class Selenium)
		//selenide não precisa do close

		//Pre condição
		PontoDoacao ponto = new PontoDoacao(
			"Papito Point", 
			"papito@point.net",
			"21510105", 100, "Ao lado da padaria",
		    "Cachorros"
		);

		open("https://petlov.vercel.app/signup");

		submeterFormulario(ponto);

		//Resultado da ação
		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("#success-page p").shouldHave(text(target));

	}
}
