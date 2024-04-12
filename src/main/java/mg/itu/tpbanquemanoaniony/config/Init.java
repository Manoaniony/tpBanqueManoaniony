/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquemanoaniony.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import mg.itu.tpbanquemanoaniony.entity.CompteBancaire;
import mg.itu.tpbanquemanoaniony.service.GestionnaireCompte;

/**
 *
 * @author ramarolahymanoaniony
 */
@ApplicationScoped
public class Init {
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        List<CompteBancaire> liste = new ArrayList<>();
        CompteBancaire compte0 = new CompteBancaire("John Lennon",150000);
        CompteBancaire compte1 = new CompteBancaire("Paul McCartney",950000);
        CompteBancaire compte2 = new CompteBancaire("Ringo Starr",20000);
        CompteBancaire compte3 = new CompteBancaire("Georges Harrisson",100000);
        liste.add(compte0);
        liste.add(compte1);
        liste.add(compte2);
        liste.add(compte3);
        Long verif = gestionnaireCompte.nbComptes();
        if(verif == 0){
           for(int i = 0;i< liste.size();i++){
              gestionnaireCompte.creerCompte(liste.get(i));
           }
        } 
    }
}
