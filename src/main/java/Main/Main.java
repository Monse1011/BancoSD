package Main;

import jakarta.xml.ws.Endpoint;
import Service.BankServiceImpl;

public class Main {

    public static void main(String[] args) {

        String url = "http://localhost:8081/BankSoap";

        Endpoint.publish(url, new BankServiceImpl());


        System.out.println("Bank SOAP Service is running");
        System.out.println("WSDL available at:");
        System.out.println(url + "?wsdl");

    }
}