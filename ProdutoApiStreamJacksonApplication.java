package br.edu.fatecpg.ProdutoAPI_Stream_Jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class ProdutoApiStreamJacksonApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ProdutoApiStreamJacksonApplication.class, args);


		String json = ConsomeAPI.enviarRequisicao();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);


		List<JsonNode> jsonNodeList = new ArrayList<>();
		jsonNode.forEach(jsonNodeList::add);

		//IMPERDIVEIS: <30 reais

		List<JsonNode> imperdiveis = jsonNodeList.stream()
				.filter(num -> num.get("price").asInt() <= 30)
				.map(node -> node.get("title"))
				.toList();

		System.out.println("LISTA DOS PRODUTOS IMPERDÍVEIS:");

		imperdiveis.forEach(System.out::println);
		System.out.println(imperdiveis.size());


		//PROMOCAO: <x reais
		System.out.println("Digite o preço da promoção:");

		Scanner scan = new Scanner(System.in);
		int preco = scan.nextInt();

		List<JsonNode> promocao = jsonNodeList.stream()
				.filter(num -> num.get("price").asInt() <= preco)
				.map(node -> node.get("title"))
				.toList();

		System.out.println("LISTA DOS PRODUTOS EM PROMOÇÃO:");
		promocao.forEach(System.out::println);
		System.out.println(promocao.size());



	}

}
