package ru.bublinoid.dao;

import ru.bublinoid.model.Customer;
import ru.bublinoid.model.Product;
import ru.bublinoid.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDAO {

    public Product findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            product = em.merge(product);
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Product> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("from Product", Product.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Product> findProductsByCustomer(Customer customer) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("select p from Product p join p.customers c where c = :customer", Product.class)
                    .setParameter("customer", customer)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
