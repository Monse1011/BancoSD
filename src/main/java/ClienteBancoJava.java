import cliente.banco.BankServiceImplService;
import cliente.banco.BankService;
import com.dataaccess.webservicesserver.NumberConversion;
import com.dataaccess.webservicesserver.NumberConversionSoapType;
import org.oorsprong.websamples.CountryInfoService;
import org.oorsprong.websamples.CountryInfoServiceSoapType;

import java.math.BigInteger;

public class ClienteBancoJava {

    public static void main(String[] args) {
        System.out.println("CLIENTE SISTEMA BANCARIO DISTRIBUIDO");
        System.out.println("*Conectado al servidor del banco*");

        try {

            BankServiceImplService bankService = new BankServiceImplService();
            BankService portBanco = bankService.getBankServiceImplPort();

            NumberConversion numberService = new NumberConversion();
            NumberConversionSoapType portNumber = numberService.getNumberConversionSoap();

            CountryInfoService countryService = new CountryInfoService();
            CountryInfoServiceSoapType portCountry = countryService.getCountryInfoServiceSoap();


            System.out.println("\n--- Crear cuentas ---");
            System.out.println("Creando cuenta para Joe con saldo inicial de 1000...");
            portBanco.createAccount("1", "Joe", 1000.0);
            System.out.println("Cuenta creada exitosamente");

            System.out.println("Creando cuenta para Elena con saldo inicial de 500...");
            portBanco.createAccount("2", "Elena", 500.0);
            System.out.println("Cuenta creada exitosamente");

            System.out.println("\n--- Depositar dinero ---");
            System.out.println("Depositando 200 en la cuenta de Joe...");
            portBanco.deposit("1", 200.0);
            System.out.println("Deposito exitoso");

            System.out.println("\n--- Realizar transferencia ---");
            System.out.println("Transfiriendo 150 de la cuenta de Joe a la cuenta de Elena...");
            portBanco.transfer("1", "2", 150.0);
            System.out.println("Transferencia exitosa");


            System.out.println("\n--- Consultar saldos ---");
            System.out.println("Consultando saldo en palabras...");

            double saldoJoe = portBanco.getBalance("1");
            double saldoElena = portBanco.getBalance("2");

            System.out.println("Saldo Joe: $ " + saldoJoe);
            String saldoJoeLetras = portNumber.numberToWords(BigInteger.valueOf((long) saldoJoe));
            System.out.println("Saldo Joe en palabras (inglés): " + saldoJoeLetras.trim());

            System.out.println("Saldo Elena: $ " + saldoElena);
            String saldoElenaLetras = portNumber.numberToWords(BigInteger.valueOf((long) saldoElena));
            System.out.println("Saldo Elena en palabras (inglés): " + saldoElenaLetras.trim());

            System.out.println("\nConsultando información de pais...");
            String isoCodeMexico = "MX";
            String pais = portCountry.countryName(isoCodeMexico);
            String capital = portCountry.capitalCity(isoCodeMexico);
            String moneda = portCountry.countryCurrency(isoCodeMexico).getSName();

            System.out.println("País: " + pais);
            System.out.println("Capital: " + capital);
            System.out.println("Moneda: " + moneda);

            System.out.println("\n--- INFORME FINAL ---");

            System.out.println("Cliente: Joe");
            System.out.println("Saldo final: " + saldoJoe);
            System.out.println("Saldo final en palabras: " + saldoJoeLetras.trim());
            System.out.println("País consultado: " + pais);
            System.out.println("Capital: " + capital);
            System.out.println("Moneda: " + moneda);

            System.out.println("\nCliente: Elena");
            System.out.println("Saldo final: " + saldoElena);
            System.out.println("Saldo final en palabras: " + saldoElenaLetras.trim());
            System.out.println("País consultado: " + pais);
            System.out.println("Capital: " + capital);
            System.out.println("Moneda: " + moneda);

            System.out.println("\nSistema distribuido funcionando correctamente");

        } catch (Exception e) {
            System.err.println("Ocurrió un error en la conexión a los servicios web:");
            e.printStackTrace();
        }
    }
}