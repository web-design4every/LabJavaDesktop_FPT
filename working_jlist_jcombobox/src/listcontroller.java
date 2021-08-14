/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
import javax.swing.ImageIcon;
import javax.swing.JList;

/**
 *
 * @author Admin
 */
public class listcontroller {
    listframe l = new listframe();
    JList list = l.getList();
    ImageIcon iconBird = new ImageIcon(this.getClass().getClassLoader().getResource("img/bird.PNG"));
    ImageIcon iconCat = new ImageIcon(this.getClass().getClassLoader().getResource("img/cat.PNG"));
    ImageIcon iconDog = new ImageIcon(this.getClass().getClassLoader().getResource("img/dog.PNG"));
    ImageIcon iconRabbit = new ImageIcon(this.getClass().getClassLoader().getResource("img/rabbit.PNG"));
    ImageIcon iconPig = new ImageIcon(this.getClass().getClassLoader().getResource("img/pig.PNG"));
    
    public listcontroller() {
        l.setVisible(true);
        l.setResizable(false);
        l.setLocationRelativeTo(null);
        
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Object select = list.getSelectedValue();
                if (select.toString().equals("Bird")) {
                    l.getLbl2().setIcon(iconBird);
                }
                if (select.toString().equals("Cat")) {
                    l.getLbl2().setIcon(iconCat);
                }
                if (select.toString().equals("Dog")) {
                    l.getLbl2().setIcon(iconDog);
                }
                if (select.toString().equals("Rabbit")) {
                    l.getLbl2().setIcon(iconRabbit);
                }
                if (select.toString().equals("Pig")) {
                    l.getLbl2().setIcon(iconPig);
                }
            }
        });
    }
    
}