package es.jmarara529.sesionesobjetoshibernateylenguajehql;

import es.jmarara529.sesionesobjetoshibernateylenguajehql.entidades.Departamento;
import es.jmarara529.sesionesobjetoshibernateylenguajehql.entidades.Trabajador;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("default");

    public static void main(String[] args) {

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            mostrarDepartamentos(entityManager);

            mostrarTrabajadores(entityManager);

            mostrarTrabajadoresDepartamento(entityManager);

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            try {
                cambiarNombrePrimerTrabajador(entityManager);
                borrarUltimoTrabajador(entityManager);
                agregarNuevoTrabajador(entityManager);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }

            buscarDepartamentoPorNumero(entityManager);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManagerFactory.close();
        }

    }

    private static void mostrarDepartamentos(EntityManager entityManager) {
        System.out.println("---- Listado de Departamentos ----");
        TypedQuery<Departamento> query = entityManager.createQuery("from Departamento", Departamento.class);
        List<Departamento> departamentoList = query.getResultList();
        departamentoList.forEach(System.out::println);
    }

    private static void mostrarTrabajadores(EntityManager entityManager) {
        System.out.println("---- Listado de Trabajadores ----");
        entityManager.createQuery("from Trabajador", Trabajador.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void mostrarTrabajadoresDepartamento(EntityManager entityManager) {
        System.out.println("---- Listado de Trabajadores por Departamento ----");
        entityManager.createQuery("from Trabajador t where t.nDep.id = 1", Trabajador.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void cambiarNombrePrimerTrabajador(EntityManager entityManager) {
        Trabajador trabajador = entityManager.createQuery("from Trabajador", Trabajador.class)
                .setMaxResults(1)
                .getSingleResult();
        trabajador.setNombre("Juan");
        entityManager.merge(trabajador);
    }

    private static void borrarUltimoTrabajador(EntityManager entityManager) {
        Trabajador trabajador = entityManager.createQuery("from Trabajador", Trabajador.class)
                .setMaxResults(1)
                .getSingleResult();
        entityManager.remove(trabajador);
    }

    private static void agregarNuevoTrabajador(EntityManager entityManager) {
        Trabajador trabajador = new Trabajador();
        trabajador.setDni("37890123h"); // Asigna un valor único al campo dni
        trabajador.setNombre("Pedro");
        trabajador.setApellido1("Pérez");
        trabajador.setApellido2("Gómez");
        trabajador.setNDep(entityManager.find(Departamento.class, 1));
        entityManager.persist(trabajador);
    }


    private static void buscarDepartamentoPorNumero(EntityManager entityManager) {
        System.out.println("---- Buscar Departamento por número ----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el número del departamento a buscar:");
        Departamento departamento = entityManager.find(Departamento.class, scanner.nextInt());
        System.out.println("Departamento con número 1: " + departamento);
    }
}
