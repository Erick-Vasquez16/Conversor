import java.io.IOException;
import java.util.HashMap;

public class Cambio {
    private double monto;
    private String divisaOrigen;
    private String divisaDestino;
    private HashMap<String, Double> conversionRates;

    public Cambio(double monto, String divisaOrigen, String divisaDestino, HashMap<String, Double> conversionRates) {
        this.monto = monto;
        this.divisaOrigen = divisaOrigen;
        this.divisaDestino = divisaDestino;
        this.conversionRates = conversionRates;
    }

    public double realizarCambio() {
        // Verificar si las divisas están en el registro de conversionRates
        if (!conversionRates.containsKey(divisaOrigen) || !conversionRates.containsKey(divisaDestino)) {
            System.out.println("Las divisas especificadas no se encuentran en el registro.");
            return -1; // O cualquier otro valor que indique un error
        }

        // Obtener los tipos de cambio para las divisas de origen y destino
        double tipoCambioOrigen = conversionRates.get(divisaOrigen);
        double tipoCambioDestino = conversionRates.get(divisaDestino);

        // Calcular el monto convertido
        double montoConvertido = (monto / tipoCambioOrigen) * tipoCambioDestino;
        return montoConvertido;
    }

    public static void main(String[] args) {
        // Obtener los datos de la API y crear una instancia de Cambio
        ConsumiendoApi api = new ConsumiendoApi();
        try {
            HashMap<String, Double> conversionRates = api.obtenerTiposDeCambio();
            Cambio cambio = new Cambio(100, "EUR", "USD", conversionRates);

            // Realizar la conversión de divisas
            double montoConvertido = cambio.realizarCambio();
            System.out.println("equivalente a " + montoConvertido + " USD.");
        } catch (IOException e) {
            System.out.println("Error al obtener los datos de la API: " + e.getMessage());
        }
    }
}
