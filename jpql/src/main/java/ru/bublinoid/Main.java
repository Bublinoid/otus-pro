package ru.bublinoid;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.bublinoid.entity.Address;
import ru.bublinoid.entity.Client;
import ru.bublinoid.entity.Phone;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("examplePU");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Address address = new Address();
            address.setStreet("123 Main Street");

            Phone phone1 = new Phone();
            phone1.setNumber("123-456-7890");

            Phone phone2 = new Phone();
            phone2.setNumber("098-765-4321");

            List<Phone> phones = Arrays.asList(phone1, phone2);

            Client client = new Client();
            client.setName("Alex");
            client.setAddress(address);
            client.setPhones(phones);

            for (Phone phone : phones) {
                phone.setClient(client);
            }

            em.persist(client);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
