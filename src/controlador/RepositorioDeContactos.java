package controlador;

import modelo.Contacto;
import persistencia.IOContacto;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RepositorioDeContactos {
    private ArrayList<Contacto> contactos;

    //implementación de Singleton
    private static RepositorioDeContactos instancia=null;
    private RepositorioDeContactos(){
        contactos=new ArrayList<>();
    }
    public static RepositorioDeContactos getInstance(){
        if(instancia==null){
            instancia=new RepositorioDeContactos();
        }
        return instancia;
    }

    public boolean agregaContacto(int id, String nombre, String email){
        Contacto c=new Contacto(id, nombre, email);
        if(contactos.contains(c)){
            return false;
        }
        return contactos.add(c);
    }

    public String[] buscarContacto(int id){
        for (Contacto contacto : contactos) {
            if(contacto.getCod()==id){
                String[] out=new String[3];
                out[0]=""+contacto.getCod();
                out[1]=contacto.getNombre();
                out[2]=contacto.getEmail();
                return out;
            }
        }
        return null;
    }

    public boolean eliminarContacto(int id){
        for(int i=0; i < contactos.size();i++){
            if(contactos.get(i).getCod()==id){
                contactos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean cargar(){
        try {
            Contacto[] misContactos= IOContacto.getInstance().load();
            contactos.clear();
            for (Contacto c : misContactos) {
                contactos.add(c);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    public boolean guardar(){
        try {
            IOContacto.getInstance().save(contactos.toArray(Contacto[]::new));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public String[][] lista() {
        String[][] lista=new String[contactos.size()][3];
        for (int i = 0; i < lista.length; i++) {
            lista[i][0]=String.valueOf(contactos.get(i).getCod());
            lista[i][1]=contactos.get(i).getNombre();
            lista[i][2]=contactos.get(i).getEmail();
        }
        return lista;
    }
}
