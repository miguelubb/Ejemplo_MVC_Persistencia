package vista;

import controlador.RepositorioDeContactos;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MenuCLI {
    //delimiters
    private static final String LINEA = "(\\r\\n|\\r|\\n)+";
    private static final String CAMPO = "\\s*;\\s*";

    private Scanner sc;
    public MenuCLI(){
        sc=new Scanner(System.in);
        sc.useDelimiter(LINEA+"|"+CAMPO);

    }
    public void mostrarMenu(){
        System.out.println("""
                *** Menu Prinpal ***
                1) Crear un contacto
                2) buscar un contacto por id
                3) Eliminar un contacto
                4) Listado de contactos
                
                0) Salir
                """);
    }
    public void run(){
        //carga de datos
        boolean ok=RepositorioDeContactos.getInstance().cargar();
        if(ok){
            System.out.println("Datos cargados satisfactoriamente...");
        }
        int op=0;
        do{
            mostrarMenu();
            op=sc.nextInt();
            switch (op){
                case 0 -> System.out.println("Adios...");
                case 1 -> crearContacto();
                case 4 -> listar();
                default -> System.out.println("Opción no válida");
            }
        }while(op!=0);
        ok=RepositorioDeContactos.getInstance().guardar();
        if(ok){
            System.out.println("datos guardados satisfactoriamente...");
        }else{
            System.out.println("Error: Datos no guardados");
        }
    }

    private void listar() {
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%5s|%-30s|%-30s%n","ID", "Nombre", "Email");
        System.out.println("------------------------------------------------------------------");
        String[][] listado=RepositorioDeContactos.getInstance().lista();
        if(listado.length==0){return;}
        for (int i = 0; i < listado.length; i++) {
            System.out.printf("%5s|%-30s|%-30s%n",listado[i][0], listado[i][1], listado[i][2]);
        }
        System.out.println("------------------------------------------------------------------");

    }

    private void crearContacto() {
        System.out.println("Ingrese los datos del contacto:");
        System.out.println("Codigo:");
        int codigo=sc.nextInt();
        System.out.println("Nombre: ");
        String nombre=sc.next();
        System.out.println("Email : ");
        String email=sc.next();
        RepositorioDeContactos repo=RepositorioDeContactos.getInstance();
        boolean ok=repo.agregaContacto(codigo, nombre, email);
        if(ok){
            System.out.println("Contacto agregado satisfactoriamente");
        }else{
            System.out.println("Contacto repetido, no se puede agregar");
        }
    }
}
