/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemanoaniony.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanquemanoaniony.entity.CompteBancaire;
import mg.itu.tpbanquemanoaniony.entity.OperationBancaire;
import mg.itu.tpbanquemanoaniony.service.GestionnaireCompte;

/**
 *
 * @author ramarolahymanoaniony
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {
    
    @Inject
    GestionnaireCompte gestionnaireCompte;
    
    private Long id;
    private CompteBancaire compte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }
    
    
    
    
    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
    } 
    
    public List<OperationBancaire> getOperationBancaire(){
        return compte.getOperations();
    }

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }
    
}
