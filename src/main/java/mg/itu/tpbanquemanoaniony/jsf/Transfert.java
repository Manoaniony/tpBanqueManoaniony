/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemanoaniony.jsf;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanquemanoaniony.entity.CompteBancaire;
import mg.itu.tpbanquemanoaniony.service.GestionnaireCompte;

/**
 *
 * @author ramarolahymanoaniony
 */
@Named(value = "transfert")
@ApplicationScoped
public class Transfert implements Serializable {
    
    private Long idSource;
    private Long idDestination;
    private int montant;
    
    @Inject
    GestionnaireCompte gestionnaireCompte;

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public void transfertCompte(){
        
    }

    /**
     * Creates a new instance of Transfert
     * @return 
     */
    public String transfertComptes() {
        CompteBancaire compteSource = gestionnaireCompte.findById(idSource);
        CompteBancaire compteDestination = gestionnaireCompte.findById(idDestination);
        gestionnaireCompte.transferer(compteSource, compteDestination, montant);
        return "listeComptes";
    }
    
}
