/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquemanoaniony.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tpbanquemanoaniony.entity.CompteBancaire;

/**
 *
 * @author ramarolahymanoaniony
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 8889,
        user = "root", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
//@RequestScoped
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     *
     * @param c
     */
    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    /**
     *
     * @return
     */
    /*public List<CompteBancaire> getAllComptes() {
        String req = "select c from CompteBancaire as c";
        TypedQuery<CompteBancaire> query = em.createQuery(req, CompteBancaire.class);
        return query.getResultList();
    }*/
    /**
     *
     * @return
     */
    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }

    /**
     *
     * @return
     */
    public Long nbComptes() {
        String req = "select count(e) from CompteBancaire as e";
        TypedQuery<Long> query = em.createQuery(req, Long.class);
        return query.getSingleResult();
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public CompteBancaire findById(Long id){
        return em.find(CompteBancaire.class , id);
    }

    
    /**
     * 
     * @param source
     * @param destination
     * @param montant 
     */
    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination, int montant) {
        /*source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
        */
        retirer(source,montant);
        deposer(destination,montant);
    }

    /**
     * 
     * @param compteBancaire
     * @return 
     */
    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    /**
     * Dépôt d'argent sur un compte bancaire.
     * @param compteBancaire
     * @param montant 
     */
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
      compteBancaire.deposer(montant);
      update(compteBancaire);
    }
    
    /**
     * Retrait d'argent sur un compte bancaire.
     * @param compteBancaire
     * @param montant 
     */
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
      compteBancaire.retirer(montant);
      update(compteBancaire);
    }

}
