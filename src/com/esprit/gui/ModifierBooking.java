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
import com.esprit.entities.Booking;
import com.esprit.services.BookingService;

/**
 *
 * @author Asus
 */
public class ModifierBooking extends BaseForm {
    
    Form current;
    public ModifierBooking(Resources res ,Booking r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Abonnement");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
       TextField bookingdate = new TextField(r.getBookingDate(), "bookingdate" , 20 , TextField.ANY);
        TextField firstdate = new TextField(r.getFirstDate(), "firstdate " , 20 , TextField.ANY);
        TextField enddate = new TextField(r.getEndDate(), "enddate " , 20 , TextField.ANY);
            
        
        
        
        
        bookingdate.setUIID("NewsTopLine");
        firstdate.setUIID("NewsTopLine");
       
         enddate.setUIID("NewsTopLine");
       
        bookingdate.setSingleLineTextArea(true);
         firstdate.setSingleLineTextArea(true);
         enddate.setSingleLineTextArea(true);
        Button Add = new Button("Add");
       Add.setUIID("Button");
       
       //Event onclick btnModifer
       
       Add.addPointerPressedListener(l ->   { 
          r.setBookingDate(bookingdate.getText());
          r.setEndDate(enddate.getText());
          r.setFirstDate(firstdate.getText());
          r.setStayId(1);
          r.setUserId(1);
      
       
       //appel fonction modfier reclamation men service
       
       if(BookingService.getInstance().modifierBooking(r)) { // if true
           new ListBooking(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListBooking(res).show();
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
                new FloatingHint(bookingdate),
                createLineSeparator(),
                new FloatingHint(firstdate),
                createLineSeparator(),
               
                new FloatingHint(enddate),
                createLineSeparator(),
              
                createLineSeparator(),//ligne de séparation
           
                Add,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}