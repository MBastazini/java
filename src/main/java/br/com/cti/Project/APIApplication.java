package br.com.cti.Project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cti.Project.model.ConverteDados;
import br.com.cti.Project.model.DadosCritica;
import br.com.cti.Project.service.ConsumirApi;

import java.util.Scanner;


@SpringBootApplication
public class APIApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(APIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumirApi = new ConsumirApi();
		String titulo;
		System.out.println("---------------------------------------------------");
		System.out.print("Digite o título do livro (em inglês; exemplo: \"Dune\"): ");
		Scanner scanner = new Scanner(System.in);
		titulo = scanner.nextLine().replace(" ", "+");

		var json = consumirApi.obterDados("https://api.nytimes.com/svc/books/v3/reviews.json?title=" + titulo + "&api-key=chave-api");
		ConverteDados converterDados = new ConverteDados();
		DadosCritica dados = converterDados.obterDados(json, DadosCritica.class);
		if (dados.status().equals("OK")) {
			String message = dados.numResults() == 0 ? "Nenhum resultado encontrado." : "Pressione ENTER para navegar entre os resultados\n*----------- Resultados (" + dados.numResults() + ") -----------*";
			System.out.println(message);
			
			dados.results().forEach(
					result -> {
						scanner.nextLine();
						System.out.println("Título: " + result.bookTitle());
						System.out.println("Autor: " + result.bookAuthor());
						System.out.println("Resumo: " + result.summary());
						System.out.println("Data de publicação [YYYY-MM-DD]: " + result.publicationDt());
						System.out.println("Escritor do artigo: " + result.byline());
						System.out.println("URL para mais informações: " + result.url());
						System.out.println("-----------------");
					}
			);
			scanner.close();
		} else {
			System.out.println("Erro: " + dados.status());
		}
	}

}
