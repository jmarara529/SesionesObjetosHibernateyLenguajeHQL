package es.jmarara529.sesionesobjetoshibernateylenguajehql.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "n_dep", nullable = false)
    private Integer id;

    @Column(name = "nombre_dep", nullable = false, length = 50)
    private String nombreDep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDep() {
        return nombreDep;
    }

    public void setNombreDep(String nombreDep) {
        this.nombreDep = nombreDep;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombreDep='" + nombreDep + '\'' +
                '}';
    }
}