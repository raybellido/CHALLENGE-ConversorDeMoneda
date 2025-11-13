import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Principal {
    public static void main(String[] args) {

        int opcion = 0 ;
        String base = "";
        String destino = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fecha = LocalDateTime.now().format(formato);
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        System.out.println("elija una opcion");
        List<String> historial = new ArrayList<>();
        while(opcion != 6) {

            System.out.println("""
                    ********************************************************************
                    sea bienvenido al conversor de moneda  :)
                    
                    1)Soles => Dolar
                    2)Dolar => Soles
                    3)Euro   => Peso Mexicano
                    4)Peso Mexicano => Euro
                    5)Historial de conversiones
                    6)Salir
                    
                    Elija una opcion valida:
                    ********************************************************************
                    """);

            opcion = lectura.nextInt();

            switch (opcion){
                case 1:
                    base ="PEN";
                    destino = "USD";
                    break;
                case 2:
                    base ="USD";
                    destino = "PEN";
                    break;
                case 3:
                    base ="EUR";
                    destino = "MXN";
                    break;
                case 4:
                    base ="MXN";
                    destino = "EUR";
                    break;
                case 5:
                    System.out.println("Historial de conversiones");
                    if(historial.isEmpty()){
                        System.out.println("Aun no hay conversiones");
                    }else{
                        historial.forEach(System.out::println);
                    }
                    break;
                case 6:
                    System.out.println("gracias por su consulta");
                    break;

                default:
                    System.out.println("opcion no valida,intente con otra opcion");

            }

            if (opcion >=1 && opcion<=4) {
                try {
                    Moneda moneda = consulta.money(base, destino);
                    System.out.println("Moneda :"+moneda.base_code()+" = "+moneda.conversion_rate()+moneda.target_code());
                    System.out.println("resultado :"+ moneda.result());
                    System.out.println("ingrese la cantidad a convertir");
                    double cantidad = lectura.nextInt();
                    double resultado = cantidad * moneda.conversion_rate();
                    System.out.println("su cambio total seria =" + resultado);
                    historial.add(fecha + " - " + base + " => " + destino + " : " + moneda.conversion_rate());

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
