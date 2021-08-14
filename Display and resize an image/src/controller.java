
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class controller {
    frame f = new frame();
    ImageIcon icon1 = new ImageIcon(this.getClass().getClassLoader().getResource("Images/6.jpg"));
    ImageIcon icon2 = new ImageIcon(this.getClass().getClassLoader().getResource("Images/2.jpg"));
    ImageIcon icon3 = new ImageIcon(this.getClass().getClassLoader().getResource("Images/3.jpg"));
    ImageIcon icon4 = new ImageIcon(this.getClass().getClassLoader().getResource("Images/4.jpg"));
    
    public controller() {
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        
        setImagesforButton(f.getjButton1(), icon1);
        setImagesforButton(f.getjButton2(), icon2);
        setImagesforButton(f.getjButton3(), icon3);
        setImagesforButton(f.getjButton4(), icon4);
        
        f.getjButton1().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImagesforLabel(f.getjLabel1(), icon1);
            }
        });

        f.getjButton2().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImagesforLabel(f.getjLabel1(), icon2);
            }
        });

        f.getjButton3().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImagesforLabel(f.getjLabel1(), icon3);
            }
        });

        f.getjButton4().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setImagesforLabel(f.getjLabel1(), icon4);
            }
        });
    }
    private void setImagesforButton(JButton jb, ImageIcon ic) {
        /*
            This fuction set a image for a button.
         */
            Image im = ic.getImage().getScaledInstance(jb.getWidth(),
                jb.getHeight(), java.awt.Image.SCALE_SMOOTH);
        jb.setIcon(new ImageIcon(im));
    }

    private void setImagesforLabel(JLabel jl, ImageIcon ic) {
        /*
            This fuction set a image for a label.
         */
        Image im = ic.getImage().getScaledInstance(jl.getWidth(),
                jl.getHeight(), java.awt.Image.SCALE_SMOOTH);
        jl.setIcon(new ImageIcon(im));
    }
}
