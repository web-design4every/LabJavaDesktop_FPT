/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhap11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author longd
 */
public class ControllerDialogDispay {
    ListCourse gi = new ListCourse();
    GUI gui = new GUI();
     
   
    public  ControllerDialogDispay(ArrayList<source> list){
        
       displayInformation(list);
     
    }
    public void displayInformation(ArrayList<source> list){
              
               gi.setResizable(false);
        gi.setLocationRelativeTo(null);
               gi.setVisible(true);
               for (source s : list) {
            gi.getTxt1().setText(s.getStockID()+"");
               gi.getTxt2().setText(s.getStockName());
               gi.getTxt3().setText(s.getAddress());
               gi.getTxt4().setText(s.getDatee());
               gi.getTxt5().setText(s.getNote());
        }
               
          }
   
   
    
   
}
