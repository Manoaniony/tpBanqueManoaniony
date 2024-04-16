/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquemanoaniony.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Objects;
import mg.itu.tpbanquemanoaniony.entity.CompteBancaire;
import mg.itu.tpbanquemanoaniony.jsf.util.Util;
import mg.itu.tpbanquemanoaniony.service.GestionnaireCompte;

/**
 *
 * @author ramarolahymanoaniony
 */
@Named(value = "transfert")
@ViewScoped
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

    public void transfertCompte() {}
    

    /**
     * Creates a new instance of Transfert
     *
     * @return
     */
    public String transfertComptes() {
        boolean erreur = false;
        CompteBancaire compteSource = gestionnaireCompte.findById(idSource);
        CompteBancaire compteDestination = gestionnaireCompte.findById(idDestination);
        if (compteSource == null) {
            Util.messageErreur("Aucun compte avec cet id : "+idSource, "Aucun compte avec cet id : "+idSource, "form:source");
            erreur = true;
        } else {
            if (compteSource.getSolde() < montant) {
                Util.messageErreur("Le solde de l'ID : " + idSource + " est insuffisant", "Le solde de l'ID : " + idSource + " est insuffisant", "form:montant");
                erreur = true;
            }
        }
        if(Objects.equals(idSource, idDestination)){
            Util.messageErreur("Il est nécessaire que le compte source et destination soient différents", "Il est nécessaire que le compte source et destination soient différents", "form:source");
            Util.messageErreur("Il est nécessaire que le compte source et destination soient différents", "Il est nécessaire que le compte source et destination soient différents", "form:destination");
            erreur = true;
        }
        if (erreur) { 
            return null;
        }
        gestionnaireCompte.transferer(compteSource, compteDestination, montant);
        //Util.addFlashInfoMessage("Transfert correctement effectué");
        Util.addFlashInfoMessage("Le transfert d'une somme de "+montant+" du compte "+idSource+ "("+compteSource.getNom()+") vers le compte "
                +idDestination+" ("+compteDestination.getNom()+") est un succes");
        return "listeComptes?faces-redirect=true";
    }
}
