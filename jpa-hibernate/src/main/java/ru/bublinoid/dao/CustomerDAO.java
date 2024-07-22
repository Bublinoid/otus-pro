package ru.bublinoid.dao;

import ru.bublinoid.model.Customer;
import ru.bublinoid.model.Product;
import ru.bublinoid.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDAO {

    public Customer findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Customer customer) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Customer customer) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            customer = em.merge(customer);
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Customer> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("from Customer", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Customer> findCustomersByProduct(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("select c from Customer c join c.products p where p = :product", Customer.class)
                    .setParameter("product", product)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
