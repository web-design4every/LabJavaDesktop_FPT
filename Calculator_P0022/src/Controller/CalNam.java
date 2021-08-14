/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.Calculator;
import java.math.BigDecimal;
import javax.swing.JButton;

/**
 *
 * @author Nam PC
 */
public class CalNam {
    Calculator c;

    private BigDecimal firstNumber;
    private BigDecimal secondNumber;
    private BigDecimal memory = new BigDecimal("0");
    //private boolean reset;
    private boolean isMR = false;
    private boolean isProcessing;
    private int operator = -1;

    public CalNam() {
        c = new Calculator();
        c.setVisible(true);

        c.getLblClear().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c.getTxtScreen().setText("0");
                pressClear();
            }
        });

        c.getBtn0().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn0());
            }
        });
        c.getBtn1().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn1());
            }
        });
        c.getBtn2().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn2());
            }
        });
        c.getBtn3().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn3());
            }
        });
        c.getBtn4().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn4());
            }
        });
        c.getBtn5().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn5());
            }
        });
        c.getBtn6().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn6());
            }
        });
        c.getBtn7().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn7());
            }
        });
        c.getBtn8().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn8());
            }
        });
        c.getBtn9().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressNumber(c.getBtn9());
            }
        });
    }
        public void pressNumber(JButton button) {
        BigDecimal temp;
        String value = button.getText();
        if (isProcessing) {
            c.getTxtScreen().setText("0");
            isProcessing = false;
            //reset = false;
        }
        isMR = false;
        if (c.getTxtScreen().getText().equals("ERROR")) {
            c.getTxtScreen().setText(button.getText());
        } else {
            temp = new BigDecimal(c.getTxtScreen().getText() + value);
            c.getTxtScreen().setText(temp + "");
        }
    }
    public void pressClear() {
        firstNumber = new BigDecimal("0");
        secondNumber = new BigDecimal("0");
        operator = -1;
    }
}
