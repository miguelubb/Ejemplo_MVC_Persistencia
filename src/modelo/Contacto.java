package modelo;

import java.util.Objects;

/**
 * Registra los datos de un contacto
 */
public class Contacto {
    private int cod;
    private String nombre;
    private String email;

    public Contacto(int cod, String nombre, String email) {
        this.cod = cod;
        this.nombre = nombre;
        this.email = email;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return cod == contacto.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cod);
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "cod=" + cod +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
