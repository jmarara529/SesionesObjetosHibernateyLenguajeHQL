package es.jmarara529.sesionesobjetoshibernateylenguajehql.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajador")
public class Trabajador {
    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "apellido1", nullable = false, length = 30)
    private String apellido1;

    @Column(name = "apellido2", nullable = false, length = 30)
    private String apellido2;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "n_dep", nullable = false)
    private Departamento nDep;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Departamento getNDep() {
        return nDep;
    }

    public void setNDep(Departamento nDep) {
        this.nDep = nDep;
    }


    @Override
    public String toString() {
        return "Trabajador{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", nDep=" + nDep +
                '}';
    }
}