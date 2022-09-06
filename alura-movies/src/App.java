import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer a conexão http e buscar os top 250 filmes

        String url = "https://imdb-api.com/en/API/Top250Movies/k_lxcad2s0";
        // String url = "https://imdb-api.com/en/API/MostPopularMovies/k_lxcad2s0";
        
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados importantes (title, poster, ratting)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println(filme.get("year"));
            System.out.println();
        }
    }
}
