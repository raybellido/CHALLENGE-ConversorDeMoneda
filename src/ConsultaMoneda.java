import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    public Moneda money(String base , String destino) {

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/8058d5103a93c4389b1d99d8/pair/"+base+"/"+destino);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch(Exception e) {
            throw new RuntimeException("error"+ e.getMessage());
        }
    }
}
