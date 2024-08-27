package br.com.cti.screenmatch.principal;

import br.com.cti.screenmatch.model.DadosSerie;
import br.com.cti.screenmatch.model.DadosTemporada;
import br.com.cti.screenmatch.model.Serie;
import br.com.cti.screenmatch.repository.SerieRepositoy;
import br.com.cti.screenmatch.service.ConsumoApi;
import br.com.cti.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a832bd42";
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private  SerieRepositoy repository;
    public Principal(SerieRepositoy repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
               
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        dadosSeries.add(dados);
        try {
            repository.save(serie);
        } catch (Exception e) {
            System.out.println("Erro ao salvar no banco de dados - possivelmente duplicado");
            System.out.print("\n\n");
        }
        String dadosFormatados = String.format("---> %s [ %s ] ---< \n\nTotal de temporadas: %d\nAvaliação: %.1f\nGênero: %s\nAtores: %s\nPoster: %s\nSinopse: %s\n --> =========== <---",
                serie.getTitulo(), serie.getAno(), serie.getTotalTemporadas(), serie.getAvaliacao(), serie.getGenero(), serie.getAtores(), serie.getPoster(), serie.getSinopse());
        System.out.println(dadosFormatados);
        System.out.print("\n");
    }

    private DadosSerie getDadosSerie() {
        System.out.print("Digite o nome da série para busca: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        int totalTemporadas = (dadosSerie.totalTemporadas() == null ? 0 : dadosSerie.totalTemporadas());

        if(totalTemporadas == 0){
            System.out.println("Serie não tem temporadas.");
        }

        for (int i = 1; i <= totalTemporadas; ++i) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }

        temporadas.forEach(System.out::println);
    }

    private void listarSeriesBuscadas(){
        List<Serie> series = new ArrayList<>();
        series = dadosSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
}