package persistencia;
import modelo.Contacto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IOContacto {
    private String fileName;
    //delimiters
    private static final String LINEA = "(\\r\\n|\\r|\\n)+";
    private static final String CAMPO = "\\s*;\\s*";
    //singleton:
    /*
    1) variable estática que guarde la instancia única
    2) método estático para recuperar la instancia
    3) constructores PRIVADOS
     */
    private static IOContacto instance=new IOContacto();
    public static IOContacto getInstance(){
        return instance;
    }
    private IOContacto(){
        fileName="files/misContactos.txt";
    }

    public Contacto[] load() throws FileNotFoundException {
        File file=new File(fileName);
        ArrayList<Contacto> contactos=new ArrayList<>();
        try(Scanner sc=new Scanner(file)){
            sc.useDelimiter(LINEA+"|"+CAMPO);
            while(sc.hasNext()){
                int cod=sc.nextInt();
                String nombre=sc.next();
                String email=sc.next();
                contactos.add(new Contacto(cod, nombre, email));
            }
        }
        return contactos.toArray(new Contacto[0]);
    }
    public void save(Contacto[] data) throws FileNotFoundException {
        File file=new File(fileName);
        try(PrintWriter out=new PrintWriter(file)){
            for (int i = 0; i < data.length; i++) {
                out.printf("%d ; %s ; %s%n",
                        data[i].getCod(), data[i].getNombre(), data[i].getEmail());
            }
        }
    }
}
