/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.services.UserService;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");


        TextField username = new TextField("", "username", 20, TextField.ANY);
         TextField firstname = new TextField("", "firstname", 20, TextField.ANY);
          TextField lastname = new TextField("", "lastname", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
       TextField introduction = new TextField("", "introduction", 20, TextField.USERNAME);
       
       TextField phone = new TextField("", "Phone", 20, TextField.PHONENUMBER);
       
        
     
        
        
        username.setSingleLineTextArea(false);
        firstname.setSingleLineTextArea(false);
        lastname.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        
        introduction.setSingleLineTextArea(false);
        phone.setSingleLineTextArea(false);
        Button next = new Button("SignUp");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
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
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        
                
        next.addActionListener((e) -> {
           
            try {
  //User(int cin,String nom, String prenom, int num_tel,Date date_naissance, String password, String email, String username , String status) {
         User u = new User();
         u.setEmail(email.getText());
         u.setFirstname(firstname.getText());
         u.setIntroduction(introduction.getText());
         u.setLastname(lastname.getText());
         u.setPassword(password.getText());
         u.setPhone(phone.getText());
         u.setUsername(username.getText());
         
                
                UserService.getInstance().addUser(u);
                sendMail(res);
                 new SignInForm(res).show();
          
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Dialog.show("Success","account is saved","OK",null);
           // new SignInForm(res).show();
        });
    }
    
    
    
    
  public void sendMail(Resources res) {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("Reintialisation mot de passe <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, "racha.aoun@hotmail.com");
            msg.setSubject("Application nom  : Confirmation du ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           //String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);//mp taw narj3lo
           String txt = "Bienvenue sur AppNom : Votre compte a été crée";
           
           
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