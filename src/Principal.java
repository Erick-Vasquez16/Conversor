import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        ConsumiendoApi api = new ConsumiendoApi();
        try {
            HashMap<String, Double> conversionRates = api.obtenerTiposDeCambio();

            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;

            while (continuar) {
                System.out.println("Bienvenido al conversor de divisas");
                // Solicitar al usuario el monto a convertir
                System.out.print("Ingrese el monto a convertir: ");
                double monto = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea

                //Menu para el usuario
                System.out.println("********Divisas********");
                System.out.println("""
                                    México: MXN
                                    Guatemala: GTQ
                                    Honduras: HNL
                                    Nicaragua: NIO
                                    Argentina: ARS
                                    Chile: CLP
                                    Brasil: BRL
                                    Colombia: COP
                                    España: EUR
                                    Estados Unidos: USD
                                    """);
                System.out.println("-----------------***-----------------");

                // Solicitar al usuario la divisa de origen
                System.out.print("Ingrese la divisa de origen (por ejemplo, USD): ");
                String divisaOrigen = scanner.nextLine().toUpperCase();
                System.out.println("-----------------------------------");

                // Solicitar al usuario la divisa de destino
                System.out.print("Ingrese la divisa de destino (por ejemplo, EUR): ");
                String divisaDestino = scanner.nextLine().toUpperCase();

                // Crear una instancia de Cambio y realizar la conversión
                Cambio cambio = new Cambio(monto, divisaOrigen, divisaDestino, conversionRates);
                double montoConvertido = cambio.realizarCambio();
                System.out.println(String.format("%.2f %s es equivalente a %.2f %s.", monto, divisaOrigen, montoConvertido, divisaDestino));
                System.out.println("************************************");

                // Preguntar al usuario si desea continuar
                System.out.print("¿Desea realizar otra conversión? (si/no): ");
                String respuesta = scanner.nextLine().toLowerCase();
                continuar = respuesta.equals("si");
                System.out.println(" ");
            }
        } catch (IOException e) {
            System.out.println("Error al obtener los datos de la API: " + e.getMessage());
        }
    }
}
