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
import com.esprit.entities.User;
import com.esprit.services.UserService;

/**
 *
 * @author Asus
 */
public class ModifierUser extends BaseForm {
    
    Form current;
    public ModifierUser(Resources res ,User r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier User");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
       TextField username = new TextField(r.getUsername(), "username", 20, TextField.ANY);
         TextField firstname = new TextField(r.getFirstname(), "firstname", 20, TextField.ANY);
          TextField lastname = new TextField(r.getLastname(), "lastname", 20, TextField.ANY);
        TextField email = new TextField(r.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField(r.getPassword(), "Password", 20, TextField.PASSWORD);
       TextField introduction = new TextField(r.getIntroduction(), "introduction", 20, TextField.USERNAME);
       
       TextField phone = new TextField(r.getPhone(), "Phone", 20, TextField.PHONENUMBER);
       
        
            
        
        
        
        
        username.setUIID("NewsTopLine");
        phone.setUIID("NewsTopLine");
        firstname.setUIID("NewsTopLine");
        lastname.setUIID("NewsTopLine");
        
        email.setUIID("NewsTopLine");
        password.setUIID("NewsTopLine");
       
        introduction.setUIID("NewsTopLine");
       
        phone.setUIID("NewsTopLine");
       
        
        phone.setSingleLineTextArea(true);
        
        introduction.setSingleLineTextArea(true);
        
        username.setSingleLineTextArea(true);
         phone.setSingleLineTextArea(true);
        firstname.setSingleLineTextArea(true);
         lastname.setSingleLineTextArea(true);
       
        email.setSingleLineTextArea(true);
         password.setSingleLineTextArea(true);
        
        Button Add = new Button("Add");
       Add.setUIID("Button");
       
       //Event onclick btnModifer
       
       Add.addPointerPressedListener(l ->   { 
         r.setEmail(email.getText());
         r.setFirstname(firstname.getText());
         r.setIntroduction(introduction.getText());
         r.setLastname(lastname.getText());
         r.setPassword(password.getText());
         r.setPhone(phone.getText());
         r.setUsername(username.getText());
      
       
       //appel fonction modfier reclamation men service
       
       if(UserService.getInstance().modifierUser(r)) { // if true
           new ListUser(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListUser(res).show();
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
               new FloatingHint(lastname),
                 createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(firstname),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                 new FloatingHint(phone),
                createLineSeparator(),
                new FloatingHint(introduction),
                createLineSeparator(),
                
                new FloatingHint(password),
                createLineSeparator(),//ligne de séparation
           
                Add,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}