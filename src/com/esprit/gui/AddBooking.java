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
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Booking;
import com.esprit.entities.Stay;
import com.esprit.services.BookingService;
import com.esprit.services.UserService;

import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Asus
 */
public class AddBooking extends BaseForm {
    
    Form current;
    public AddBooking(Resources res ,Stay s) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Abonnement");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
      TextField bookingdate = new TextField("", "bookingdate" , 20 , TextField.ANY);
        TextField firstdate = new TextField("", "firstdate " , 20 , TextField.ANY);
        TextField enddate = new TextField("", "enddate " , 20 , TextField.ANY);
            
        
        
        
        
        bookingdate.setUIID("NewsTopLine");
        firstdate.setUIID("NewsTopLine");
       
         enddate.setUIID("NewsTopLine");
       
        bookingdate.setSingleLineTextArea(true);
         firstdate.setSingleLineTextArea(true);
         enddate.setSingleLineTextArea(true);
        //dateexpiration.setSingleLineTextArea(true);
        Button Add = new Button("Add");
       Add.setUIID("Button");
       
       //Event onclick btnModifer
       
       Add.addPointerPressedListener(l ->   { 
       if ((bookingdate.getText().length()!=0)||( enddate.getText().length()!=0)){
           Booking e = new Booking();
          e.setBookingDate(bookingdate.getText());
          e.setEndDate(enddate.getText());
          e.setFirstDate(firstdate.getText());
          e.setStayId(s.getId());
          e.setUserId(1);
      
       
       //appel fonction modfier reclamation men service
       
      /* if(BookingService.getInstance().addBooking(e)) { // if true
          // sendMail( res);
           new ListBooking(res).show();
       }*/  
      BookingService.getInstance().Bookingadd(e);
       }
       
       else 
           Dialog.show("Controle de saisie ", "Champs vide", null,"OK");
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
    
    
    
    
       
    
  public void sendMail(Resources res) {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("Confirmation <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, "racha.aoun@hotmail.com");
            msg.setSubject("Application nom  : Confirmation du ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           //String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);//mp taw narj3lo
           String txt = "Bienvenue sur AppNom : Votre abonnement a été crée";
           
           
           msg.setText(txt);
           
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"racha.aoun@esprit.tn","14355402");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
    
  
}