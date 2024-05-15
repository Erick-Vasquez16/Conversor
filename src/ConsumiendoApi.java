import com.google.gson.Gson;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConsumiendoApi {

    public HashMap<String, Double> obtenerTiposDeCambio() throws IOException {
        // URL de la API de tipos de cambio
        String apiUrl = "https://v6.exchangerate-api.com/v6/f08f960a53beea43573add0c/latest/USD";

        // Realizar la solicitud HTTP GET a la API
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Leer la respuesta JSON de la API
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parsear la respuesta JSON y obtener los tipos de cambio
        HashMap<String, Double> conversionRates = new HashMap<>();
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
        Iterator<String> keys = rates.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Double value = rates.getDouble(key);
            conversionRates.put(key, value);
        }

        return conversionRates;
    }
}
