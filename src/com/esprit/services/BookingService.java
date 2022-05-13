/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Booking;
import com.esprit.entities.Stay;
import com.esprit.entities.User;
import static com.esprit.services.StayService.resultOk;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author raoun
 */
public class BookingService {
      
    public ArrayList<Booking> bookings;
    public static BookingService instance = null;
    private ConnectionRequest req;
     Resources res;
      public static boolean resultOk = true;
     
    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;

    }

    public BookingService() {
        req = new ConnectionRequest();
    }
    
    public ArrayList<Booking> parseTasks(String jsonText){
        try {
            bookings=new ArrayList<>();
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
        return bookings;
    }
       public  boolean modifierBooking(Booking u)  {
        ///user/ajouter/utilisateur?nom=test&prenom=test&adresse=null&email=test2@gmail.com&password=test&numTel=584999&username=testt&status=active
	   
        String url = Statics.BASE_URL + "/booking/booking/modifier/"+u.getId()+"?bookingDate=" + u.getBookingDate()+ "&firstDate=" + u.getFirstDate()+ "&endDate=" + u.getEndDate()+ "&stayId=" + u.getStayId()+ "&usersId=" + u.getUserId();
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
    
        public boolean addBooking(Booking u) {
   //  String url = Statics.BASE_URL +"/coupon/ajouter/json/?code="+h.getCode()+"&pourcentageReduction="+h.getPourcentageReduction();
      String url = Statics.BASE_URL + "/booking/booking/ajouter/?bookingDate=" + u.getBookingDate()+ "&firstDate=" + u.getFirstDate()+ "&endDate=" + u.getEndDate()+ "&stayId=" + u.getStayId()+ "&usersId=" + u.getUserId();
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
    
    
 public void Bookingadd(Booking u)  {
        ///user/ajouter/utilisateur?nom=test&prenom=test&adresse=null&email=test2@gmail.com&password=test&numTel=584999&username=testt&status=active
	   
       String url = Statics.BASE_URL + "/booking/booking/ajouter/?bookingDate=" + u.getBookingDate()+ "&firstDate=" + u.getFirstDate()+ "&endDate=" + u.getEndDate()+ "&stayId=" + u.getStayId()+ "&usersId=" + u.getUserId();
     
        req.setUrl(url);
      
    
    // MailSender.sendMail("racha.aoun@hotmail.com");
     // sendMail();
      req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
   
    }
    
      
      
      public ArrayList<Booking> getAllBookings(){
        String url = Statics.BASE_URL+"/booking/booking/list";
       
        
       ArrayList<Booking> result = new ArrayList<>();
        
        
        req.setUrl(url);
        req.setPost(false);
        
        
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {

                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("booking");
                   System.out.println("lissst"+mapReclamations);
                    for(Map<String, Object> obj : listOfMaps) {
                        Booking re = new  Booking();

                        String bookingDate = obj.get("bookingDate").toString();
                        String firstDate =obj.get("firstDate").toString();
                        String endDate =obj.get("endDate").toString();
                      //  String photo =obj.get("photo").toString();
                       
                              float id = Float.parseFloat(obj.get("id").toString());
                      re.setId((int)id);
                      re.setBookingDate(bookingDate);
                      re.setFirstDate(firstDate);
                      re.setEndDate(endDate);
                   
                     
                    
                      
                        result.add(re);
          System.out.println("data "+obj.get("firstDate").toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
             }

        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return result;
    }
      
          public boolean deleteBooking(int id ) {
        String url = Statics.BASE_URL +"/booking/booking/supprimer?id="+id;
        
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
    
      
}
