/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;
//import com.sendgrid.*;
import com.codename1.io.CharArrayReader;
import com.esprit.entities.User;
import java.util.*;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;


import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.gui.ListUser;
import com.esprit.gui.SignUpForm;
import com.esprit.gui.ProfileForm;
import com.esprit.gui.NewsfeedForm;
import com.esprit.utils.SessionManager;
import  com.esprit.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
/*
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;*/
/**
 *
 * @author raoun
 */
public class UserService {
   
    
    public ArrayList<User> users;
    public static UserService instance = null;
    private ConnectionRequest req;
     Resources res;
      public static boolean resultOk = true;
     
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;

    }

    public UserService() {
        req = new ConnectionRequest();
    }
    
    public ArrayList<User> parseTasks(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
     
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return users;
    }
       public  boolean modifierUser(User u)  {
        ///user/ajouter/utilisateur?nom=test&prenom=test&adresse=null&email=test2@gmail.com&password=test&numTel=584999&username=testt&status=active
	   
        String url = Statics.BASE_URL + "/admin/user/user/modifier/"+u.getId()+"?username=" + u.getUsername()+ "&password=" + u.getPassword()+ "&firstname=" + u.getFirstname()+ "&lastname=" + u.getLastname()+ "&email=" + u.getEmail()+ "&phone=" +u.getPhone()+ "&introduction=" +u.getIntroduction();
       req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
    }
    
    
    
    public void addUser(User u)  {
        ///user/ajouter/utilisateur?nom=test&prenom=test&adresse=null&email=test2@gmail.com&password=test&numTel=584999&username=testt&status=active
	   
       String url = Statics.BASE_URL + "/admin/user/user/ajouter/?username=" + u.getUsername()+ "&password=" + u.getPassword()+ "&firstname=" + u.getFirstname()+ "&lastname=" + u.getLastname()+ "&email=" + u.getEmail()+ "&phone=" +u.getPhone()+ "&introduction=" +u.getIntroduction();
       req.setUrl(url);
      
    
    // MailSender.sendMail("racha.aoun@hotmail.com");
     // sendMail();
      req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
   
    }
    
      public ArrayList<User> getAllUsers(){
        String url = Statics.BASE_URL+"/admin/user/user/list";
       
        
       ArrayList<User> result = new ArrayList<>();
        
        
        req.setUrl(url);
        req.setPost(false);
        
        
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {

                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("user");
                   System.out.println("lissst"+mapReclamations);
                    for(Map<String, Object> obj : listOfMaps) {
                        User re = new User();

                        String firstname = obj.get("firstname").toString();
                        String lastname =obj.get("lastname").toString();
                        String email =obj.get("email").toString();
                      //  String photo =obj.get("photo").toString();
                        String password =obj.get("password").toString();
                        String username =obj.get("username").toString();
                        String introduction =obj.get("introduction").toString();
                        String phone =obj.get("phone").toString();
                              float id = Float.parseFloat(obj.get("id").toString());
                      re.setId((int)id);
                     re.setIntroduction(introduction);
                      re.setPassword(password);
                     // re.setPhoto(photo);
                       re.setFirstname(firstname);
                       re.setLastname(lastname);
                       re.setPhone(phone);
                        re.setEmail(email);
                      
                        result.add(re);
          System.out.println("data "+obj.get("firstname").toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
             }

        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return result;
    }
      
   
    
        public void signin(TextField email ,TextField password ){
        
             String url = Statics.BASE_URL+"/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
             req.setUrl(url);
            
            req.addResponseListener( (e) -> {
                  JSONParser j = new JSONParser(); 
                String json= new String (req.getResponseData()); 
                // System.out.println(new String(req.getResponseData()).equals(new String('"'+"failed"+'"')));
                         if (new String(req.getResponseData()).equals(new String('"'+"failed"+'"')) )   {
                             Dialog.show("Echec d'authentification", "Email oou Mot de passe est incorrecte", null);
                             }
                      
                      else 
                         {
                             System.out.println("**** user connected");
                             System.out.println("data =====> "+json);
                             Map<String,Object> user;
                      try {
                          user = j.parseJSON((new CharArrayReader(json.toCharArray())));
                    


                              String s="";
                           
                              s=user.get("id").toString();
                           


                       int id = Integer.parseInt(s.substring(0, s.length() - 2));

                       SessionManager.setId(id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                       //SessionManager.setAdresse(user.get("adresse").toString());
                       SessionManager.setNom(user.get("nom").toString());
                       SessionManager.setPrenom(user.get("prenom").toString());
                       SessionManager.setPassoword(user.get("password").toString());
                       //SessionManager.setUserName(user.get("username").toString());
                       SessionManager.setEmail(user.get("email").toString());

                       //photo 

                       if(user.get("email") != null)
                        //   SessionManager.setPhoto(user.get("photo").toString());


                             System.out.println("Utilisateur connecté \n EMAIL : "+SessionManager.getEmail()+" \n ID : "+SessionManager.getId()); 

                      
                       System.out.println(email.getText().equals("admin@gmail.com"));
                       //if(email.getText().equals("admin@gmail.com"))
                        //   new AdminHome(res).show();
                      //else 
                      User u= new User();
                          new ProfileForm(res, u).show();
                      


                      }catch(Exception ex) {
                 ex.printStackTrace();
             }
                     }
                 });
    

              NetworkManager.getInstance().addToQueueAndWait(req); 

        }
  /*  
    public ArrayList<User> profile(User u) {
        ArrayList<User> result = new ArrayList<>();
        String utl = Statics.BASE_URL +"/user/detail/"+u.getId();
        req.setUrl(utl);
        req.setPost(false);
       
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {

                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("user");
                 
                    for(Map<String, Object> obj : listOfMaps) {
                        User user = new User();
                        String nom = obj.get("nom").toString();
                             String prenom = obj.get("prenom").toString();
                                  String email = obj.get("email").toString();
                                       String photo = obj.get("photo").toString();
                                            String numerotelephone = obj.get("numerotelephone").toString();
                                                   float id = Float.parseFloat(obj.get("id").toString());
                user.setId((int)id);
               
                       
                     
                       user.setNom(nom);
                       user.setPrenom(prenom);
                       user.setEmail(email);
                      
                            result.add(user);
                            
          System.out.println("data "+obj.get("email").toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
        
        
        

    }
    
*/
    /*  
    public boolean editProfile(TextField nom , TextField prenom,TextField adresse ,TextField email,TextField password,TextField numtel,TextField date_naissance) {
        String url = Statics.BASE_URL + "/user/edit/profile/"+SessionManager.getId()+"?nom=" + nom.getText() + "&prenom=" + prenom.getText() + "&adresse=" +adresse.getText()+ "&email=" +email.getText()+ "&password=" + password.getText()+ "&numTel=" +numtel.getText()+"&dateNaissance=" +date_naissance.getText();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
                 
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        
         return resultOk;

    }
        */
        
    /*
    public void sendEmail() throws IOException{
        
    Email from = new Email("racha.aoun@hotmail.com");
    String subject = "Sending with SendGrid is Fun";
    Email to = new Email("racha.aoun@hotmail.com");
    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid("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");
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }
    }
    */
    
    /*
    public void sendMail(){
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
            
          // String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);//mp taw narj3lo
           String txt = "Bienvenue sur AppNom : Tapez ce mot de passe : dans le champs requis et appuiez sur confirmer";
           
           
          msg.setText(txt);
           
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"racha.aoun@esprit.tn","14355402");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
            
        
        }catch(Exception e ){
            System.out.println(e.getMessage());
            
        }
    }
    */
    
    public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"/admin/user/utilisateur/supprimer/?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
           public void signin2(TextField email ,TextField password ){
        
             String url = Statics.BASE_URL+"/signin?email="+email.getText()+"&password="+password.getText();
             req.setUrl(url);
            
            req.addResponseListener( (e) -> {
                  JSONParser j = new JSONParser(); 
                String json= new String (req.getResponseData()); 
                // System.out.println(new String(req.getResponseData()).equals(new String('"'+"failed"+'"')));
                         if (new String(req.getResponseData()).equals(new String('"'+"failed"+'"')) )   {
                             Dialog.show("Echec d'authentification", "Email oou Mot de passe est incorrecte", null);
                             }
                      
                      else 
                         {
                             System.out.println("**** user connected");
                             System.out.println("data =====> "+json);
                             Map<String,Object> user;
                      try {
                          user = j.parseJSON((new CharArrayReader(json.toCharArray())));
                    


                              String s="";
                           
                              s=user.get("id").toString();
                           


                       int id = Integer.parseInt(s.substring(0, s.length() - 2));

                       SessionManager.setId(id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                       //SessionManager.setAdresse(user.get("adresse").toString());
                       SessionManager.setNom(user.get("nom").toString());
                       SessionManager.setAdresse(user.get("adresse").toString());
                       SessionManager.setPrenom(user.get("prenom").toString());
                       SessionManager.setPassoword(user.get("password").toString());
                       //SessionManager.setUserName(user.get("username").toString());
                       SessionManager.setEmail(user.get("email").toString());

//                        SessionManager.setPhoto(user.get("photo").toString());

                       //photo 
                       String p = user.get("roles").toString();
                       if(user.get("email") != null)
                        //   SessionManager.setPhoto(user.get("photo").toString());


                             System.out.println("Utilisateur connecté \n EMAIL : "+SessionManager.getEmail()+" \n ID : "+SessionManager.getId()); 

                      
                       System.out.println(p);
                        if(p.equals("[ROLE_ADMIN]"))
                           new ListUser(res).show();
                       else 
                        {
                                 User u = new User();
                          new ProfileForm(res,  u).show();
                        }


                     

                      }catch(Exception ex) {
                 ex.printStackTrace();
             }
                     }
                 });
    

              NetworkManager.getInstance().addToQueueAndWait(req); 

        }
     
    
}
