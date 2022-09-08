import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer a conexão http e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_lxcad2s0";
        // String url = "https://imdb-api.com/en/API/MostPopularMovies/k_lxcad2s0";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        // String url = "https://api.nasa.gov/planetary/apod?api_key=xUagqA27ciyidggj6UoqRp2oea7RvOQeh05B37d8"; 
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 2; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo() );
            System.out.println();
        }
    }
}
