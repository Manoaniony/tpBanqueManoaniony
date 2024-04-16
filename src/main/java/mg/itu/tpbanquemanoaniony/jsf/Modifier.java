/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemanoaniony.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanquemanoaniony.entity.CompteBancaire;
import mg.itu.tpbanquemanoaniony.jsf.util.Util;
import mg.itu.tpbanquemanoaniony.service.GestionnaireCompte;

/**
 *
 * @author ramarolahymanoaniony
 */
@Named(value = "modifier")
@ViewScoped
public class Modifier implements Serializable {
    
    

    @Inject
    GestionnaireCompte gestionnaireCompte;
    
    private Long id;
    private CompteBancaire compte;
    private String oNom;

    public String getoNom() {
        return oNom;
    }

    public void setoNom(String oNom) {
        this.oNom = oNom;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }
 
    public void loadCompte() {
        compte = gestionnaireCompte.findById(id);
        oNom = compte.getNom();
    }
    
    public String update(){
        CompteBancaire compteBancaire = gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Le changement du nom " + oNom + " en " + compteBancaire.getNom() + " a été couronné de succès.");
        return "listeComptes?faces-redirect=true";
    }
    
}
