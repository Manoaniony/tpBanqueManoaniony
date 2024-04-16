/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemanoaniony.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanquemanoaniony.entity.CompteBancaire;
import mg.itu.tpbanquemanoaniony.jsf.util.Util;
import mg.itu.tpbanquemanoaniony.service.GestionnaireCompte;

/**
 *
 * @author ramarolahymanoaniony
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    @Inject
    GestionnaireCompte gestionnaireCompte;

    private String nom;
    
    @PositiveOrZero
    private int solde;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String action() {
        CompteBancaire compte = new CompteBancaire(nom, solde);
        gestionnaireCompte.creerCompte(compte);
        Util.addFlashInfoMessage("L'ajout a été une réussite");
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

}
