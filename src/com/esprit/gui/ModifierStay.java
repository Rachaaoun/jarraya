/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Stay;
import com.esprit.services.StayService;

/**
 *
 * @author Asus
 */
public class ModifierStay extends BaseForm {
    
    Form current;
    public ModifierStay(Resources res ,Stay r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Abonnement");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
       TextField capacity = new TextField(r.getCapacity(), "capacity" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription(), "description " , 20 , TextField.ANY);
        
        TextField photo = new TextField(r.getPhoto(), "photo " , 20 , TextField.ANY);
        TextField startdateev = new TextField(r.getStartdateav(), "startdateev " , 20 , TextField.ANY);
        
        TextField enddateev = new TextField(r.getEnddateav(), "enddateev " , 20 , TextField.ANY);
         TextField nbBooked = new TextField(r.getNbBooked(), "nb Booked " , 20 , TextField.ANY);
         
            
        
        
        
        
        capacity.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        photo.setUIID("NewsTopLine");
        startdateev.setUIID("NewsTopLine");
        
        enddateev.setUIID("NewsTopLine");
        nbBooked.setUIID("NewsTopLine");
       
        
        enddateev.setSingleLineTextArea(true);
         nbBooked.setSingleLineTextArea(true);
        photo.setSingleLineTextArea(true);
         startdateev.setSingleLineTextArea(true);
       
        capacity.setSingleLineTextArea(true);
         description.setSingleLineTextArea(true);
        
        Button Add = new Button("Add");
       Add.setUIID("Button");
       
       //Event onclick btnModifer
       
       Add.addPointerPressedListener(l ->   { 
          r.setCapacity(capacity.getText());
          r.setDescription(description.getText());
          r.setEnddateav(enddateev.getText());
          r.setStartdateav(startdateev.getText());
          r.setNbBooked(nbBooked.getText());
          r.setPhoto(photo.getText());
           
      
       
       //appel fonction modfier reclamation men service
       
       if(StayService.getInstance().modifieStay(r)) { // if true
           new ListStay(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListStay(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
         Button lactiver = new Button("Activer");
        
                lactiver.requestFocus();
        lactiver.addActionListener(e ->{
           // CarteService.getInstance().activate(r);
            
        } );
            
            
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(capacity),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(photo),
                createLineSeparator(),
                new FloatingHint(startdateev),
                createLineSeparator(),
                
                new FloatingHint(enddateev),
                createLineSeparator(),
                new FloatingHint(nbBooked),
                createLineSeparator(),
              
                createLineSeparator(),//ligne de séparation
           
                Add,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}