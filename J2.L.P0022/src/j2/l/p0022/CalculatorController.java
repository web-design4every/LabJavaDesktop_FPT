/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j2.l.p0022;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class CalculatorController {

    BigDecimal firstvalue = new BigDecimal("0");
    BigDecimal secondvalue = new BigDecimal("0");
    BigDecimal memory = new BigDecimal("0");
    private int operator = -1;
    private boolean resetStatusNewInput = false;
    
    Calculator c = new Calculator();
    JButton btn0 = c.getBtn0();
    JButton btn1 = c.getBtn1();
    JButton btn2 = c.getBtn2();
    JButton btn3 = c.getBtn3();
    JButton btn4 = c.getBtn4();
    JButton btn5 = c.getBtn5();
    JButton btn6 = c.getBtn6();
    JButton btn7 = c.getBtn7();
    JButton btn8 = c.getBtn8();
    JButton btn9 = c.getBtn9();
    JButton btnAdd = c.getBtnAdd();
    JButton btnSub = c.getBtnSub();
    JButton btnDiv = c.getBtnDiv();
    JButton btnMul = c.getBtnMul();
    JButton btnMC = c.getBtnMC();
    JButton btnMR = c.getBtnMR();
    JButton btnMadd = c.getBtnMadd();
    JButton btnMsub = c.getBtnMsub();
    JButton btnDot = c.getBtndot();
    JButton btnResult = c.getBtnResult();
    JButton btnChange = c.getBtnChange();
    JButton btnSquare = c.getBtnSquare();
    JButton btnPercent = c.getBtnPercent();
    JButton btn1x = c.getBtn1x();
    JLabel lblClear = c.getLblClear();
    JTextField txtResult = c.getTxtResult();

    public CalculatorController() {
        c.setVisible(true);
        txtResult.setText("0");
        c.getTxtResult().setEditable(false);
        c.setLocationRelativeTo(null);
        c.setResizable(false);
        c.pack();
        
        lblClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c.getTxtResult().setText("0");
                firstvalue = new BigDecimal("0");
                secondvalue = new BigDecimal("0");
                resetStatusNewInput = true;
                operator =-1;
            }
        });
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn0);
            }
        });
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn1);
            }
        });
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn2);            }
        });
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn3);
            }
        });
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn4);
            }
        });
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn5);
            }
        });
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn6);
            }
        });
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn7);
            }
        });
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn8);
            }
        });
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Press(btn9);
            }
        });
        btnDiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculate();
                setOperator(4);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculate();
                setOperator(1);
            }
        });
        btnSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculate();
                setOperator(2);
            }
        });
        btnMul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculate();
                setOperator(3);
            }
        });
        btnResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!c.getTxtResult().getText().equalsIgnoreCase("Error")) {
                    calculate();
                    //reset operator after calculate to use new operator
                    operator=-1;
                }else{
                    c.getTxtResult().setText(firstvalue.toPlainString());
                }
                resetStatusNewInput=true;
            }
        });
        
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String screen = c.getTxtResult().getText();
                    //check if current value of input number is 0
                    if (resetStatusNewInput) {
                        c.getTxtResult().setText("0.");
                        resetStatusNewInput = false;
                   }
                    else{
                        //check if screen already has '.' then not apend '.' more
                        if (!screen.contains(".")) {
                            c.getTxtResult().setText(screen+".");
                        }else{
                            c.getTxtResult().setText(screen);
                        }
                    }
            }
        }); 
        
        btnMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                  //reset meory to 0
                  memory = new BigDecimal("0");
            }
        });

        btnMadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //add number on screen to memory
                BigDecimal value = new BigDecimal(c.getTxtResult().getText());
                memory = memory.add(value);
                resetStatusNewInput = true;
            }
        });

        btnMR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //show memory to screen  
                c.getTxtResult().setText(memory+"");
            }
        });

        btnMsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //square number on screen to memory
                BigDecimal value = new BigDecimal(c.getTxtResult().getText());
                memory = memory.subtract(value);
                resetStatusNewInput = true;
            }
        });

        btnSquare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BigDecimal value = getValue();
                //check if value on screen is >= 0 then action button sqrt, else set error
                if (value.doubleValue()>=0) {
                    double s = Math.sqrt(value.doubleValue());
                    firstvalue= new BigDecimal(s+"");
                    c.getTxtResult().setText(firstvalue.setScale(10, RoundingMode.CEILING).stripTrailingZeros().toPlainString());
                    operator = -1;
                }else{
                    c.getTxtResult().setText("Error");
                }
                resetStatusNewInput = true;
            }
        });

        btnPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BigDecimal temp = getValue();
                //divide 100 to get percent of screen number
                firstvalue = new BigDecimal(temp.divide(new BigDecimal("100"))+"");
                c.getTxtResult().setText(firstvalue+"");
                //reset operator if before we use operator
                operator = -1;
                resetStatusNewInput = true;
            }
        });

        btn1x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BigDecimal value = getValue();
                //check value on screen if equal 0 then set error
                if (value.doubleValue()==0) {
                    c.getTxtResult().setText("Error");
                }else{
                    BigDecimal result = new BigDecimal("1").divide(value,10,RoundingMode.HALF_EVEN).stripTrailingZeros();
                    firstvalue = new BigDecimal(result.toPlainString());
                    c.getTxtResult().setText(firstvalue+"");
                    operator = -1;
                }
                resetStatusNewInput = true;
            }
        });

        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BigDecimal value = getValue();
                //negate screen value
                Double result = -value.doubleValue();
                firstvalue = new BigDecimal(result+"");
                c.getTxtResult().setText(firstvalue.setScale(10, RoundingMode.CEILING).stripTrailingZeros().toPlainString());
                operator = -1;
                resetStatusNewInput = true;
            }
        });
    }    

    public void setOperator(int operator) {
        this.operator = operator;
    }
    
    public void Press(JButton btn){
        BigDecimal temp = new BigDecimal("0");
        String value = btn.getText();
        if (resetStatusNewInput) {
            c.getTxtResult().setText("0");
            resetStatusNewInput=false;
        }
        temp = new BigDecimal(c.getTxtResult().getText()+value);
        c.getTxtResult().setText(temp+"");
    }
    
    public BigDecimal getValue(){
        BigDecimal temp = new BigDecimal("0");
        String value = c.getTxtResult().getText();
        //if on screen is not a number then getValue() return to first number has saved before
        //else getValue() wil return number on sreen
        try {
            temp = new BigDecimal(value);
        } catch (Exception e) {
            return firstvalue;
        }
        return temp;
    }
    
    public void calculate(){
        if (!resetStatusNewInput) {
            if (operator==-1) {
                firstvalue =getValue();
            }else{
                secondvalue = getValue();
                switch(operator){
                    case 1:
                        firstvalue = firstvalue.add(secondvalue).setScale(10, RoundingMode.CEILING).stripTrailingZeros();
                        break;
                    case 2:
                        firstvalue = firstvalue.subtract(secondvalue).setScale(10, RoundingMode.CEILING).stripTrailingZeros();
                        break;
                    case 3:
                        firstvalue = firstvalue.multiply(secondvalue).setScale(10, RoundingMode.CEILING).stripTrailingZeros();
                        break;
                    case 4:
                        //check operator '/' for 0
                        if (secondvalue.doubleValue()==0) {
                            c.getTxtResult().setText("Error");
                            resetStatusNewInput = true;
                            return;
                        }else{
                            firstvalue = firstvalue.divide(secondvalue,10,RoundingMode.HALF_EVEN).stripTrailingZeros();
                            break;
                        }
                }
            }
            c.getTxtResult().setText(firstvalue.toPlainString());
            resetStatusNewInput = true;
        }
    }
}
