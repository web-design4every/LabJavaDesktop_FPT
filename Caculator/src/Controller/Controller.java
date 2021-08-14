/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.CaculatorGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class Controller implements ActionListener {

    CaculatorGUI c;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn4;
    JButton btn5;
    JButton btn6;
    JButton btn7;
    JButton btn8;
    JButton btn9;
    JButton btn0;
    JTextField txtDisplay;
    JButton btnPlus;
    JButton btnSub;
    JButton btnMul;
    JButton btnDiv;
    JButton btnRes;
    JButton btnClear;
    JButton btnPercent;
    JButton btnInvert;
    JButton btnDot;
    JButton btnSqrt;
    JButton btnChange;
    JButton btnMPlus;
    JButton btnMSub;
    JButton btnMR;
    JButton btnMC;

    boolean process = false;
    boolean reset = false;
    int operate = -1;       // plus, sub, mul, div
    BigDecimal firstNum = new BigDecimal("0");
    BigDecimal secondNum;
    BigDecimal memory = new BigDecimal("0");
    boolean isMR = false;

    public Controller() {
        c = new CaculatorGUI();
        c.setVisible(true);

        setVariable();

        txtDisplay.setEditable(false);

        btnMR.setEnabled(false);

        pressNumber();
        pressOperator();
        pressResult();
        pressClear();
        pressPercen();
        pressInvert();
        pressDot();
        pressSqrt();
        pressChange();
        pressMPlus();
        pressMR();
        pressMC();
        pressMSub();
    }

    private void setVariable() {
        btn1 = c.getBtn1();
        btn2 = c.getBtn2();
        btn3 = c.getBtn3();
        btn4 = c.getBtn4();
        btn5 = c.getBtn5();
        btn6 = c.getBtn6();
        btn7 = c.getBtn7();
        btn8 = c.getBtn8();
        btn9 = c.getBtn9();
        btn0 = c.getBtn0();
        txtDisplay = c.getTxtDisplay();
        btnPlus = c.getBtnPlus();
        btnSub = c.getBtnSub();
        btnMul = c.getBtnMulti();
        btnDiv = c.getBtnDivide();
        btnRes = c.getBtnDisplay();
        btnClear = c.getBtnClear();
        btnPercent = c.getBtnModun();
        btnInvert = c.getBtnReverseNumber();
        btnDot = c.getBtnDot();
        btnSqrt = c.getBtnSquareRoot();
        btnChange = c.getBtnReverseOperator();
        btnMPlus = c.getBtnMPlus();
        btnMSub = c.getBtnMSub();
        btnMR = c.getBtnMR();
        btnMC = c.getBtnMC();
    }

    void pressSqrt() {
        btnSqrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                double res = 0;
                try {
                    if (getNumber().doubleValue() < 0) {
                        txtDisplay.setText("ERROR");
                        reset = true;
                        return;
                    }
                    res = Math.sqrt(getNumber().doubleValue());
                } catch (Exception e) {
                    if (firstNum.doubleValue() < 0) {
                        txtDisplay.setText("ERROR");
                        reset = true;
                        return;
                    }
                    res = Math.sqrt(firstNum.doubleValue());
                }
                txtDisplay.setText(res + "");
                reset = true;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (process == true || reset == true) {
            txtDisplay.setText("");
            process = false;
            reset = false;
        }
        String txt = e.getActionCommand();      // get text from button
        String temp = txtDisplay.getText() + txt;
        BigDecimal number = new BigDecimal(temp);
        txtDisplay.setText(number + "");
    }

    public void pressClear() {
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operate = -1;
                firstNum = new BigDecimal("0");
                process = false;
                txtDisplay.setText("0");
            }
        });
    }

    public void operate(int operate) {
        caculator();
        this.operate = operate;
        process = true;
    }

    // add action listener for 0-9
    public void pressNumber() {
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btn0.addActionListener(this);
    }

    void pressDot() {
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (process == true || reset == true) {
                    txtDisplay.setText("0");
                    process = false;
                    reset = false;
                }
                if (!txtDisplay.getText().contains(".")) {
                    txtDisplay.setText(txtDisplay.getText() + ".");
                }
            }
        });
    }

    private void pressOperator() {
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operate(1);
            }
        });
        btnSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operate(2);
            }
        });
        btnMul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operate(3);
            }
        });
        btnDiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operate(4);
            }
        });
    }

    public BigDecimal getNumber() {
        return new BigDecimal(txtDisplay.getText());
    }

    public void caculator() {
        try {
            if (process == false) {
                if (operate == -1) {
                    firstNum = getNumber();
                } else {
                    secondNum = getNumber();
                    switch (operate) {
                        case 1:
                            firstNum = firstNum.add(secondNum).stripTrailingZeros();
                            break;
                        case 2:
                            firstNum = firstNum.subtract(secondNum).stripTrailingZeros();
                            break;
                        case 3:
                            firstNum = firstNum.multiply(secondNum).stripTrailingZeros();
                            break;
                        case 4:
                            if (secondNum.toString().equals("0")) {
                                txtDisplay.setText("ERROR");
//                                reset = true;
                                return;
                            }
                            firstNum = (firstNum.divide(secondNum, 15, RoundingMode.HALF_UP)).stripTrailingZeros();
                            break;
                    }
                }
                if (txtDisplay.getText().equals("-0")) {
                    return;
                }
                txtDisplay.setText(firstNum.toPlainString());
            }
        } catch (Exception e) {
        }
    }

    public void pressResult() {
        btnRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caculator();
                operate = -1;
                reset = true;
            }
        });
    }

    public void pressPercen() {
        btnPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                double res = 0;
                try {
                    res = getNumber().doubleValue() / 100;

                } catch (Exception e) {
                    res = firstNum.doubleValue() / 100;
                }
                txtDisplay.setText(res + "");
                reset = true;
//                process = true;
            }
        });
    }

    private void pressInvert() {
        btnInvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                double res = 0;
                try {
                    if (getNumber().doubleValue() == 0) {
                        txtDisplay.setText("ERROR");
                        reset = true;
                        return;
                    }
                    res = 1 / getNumber().doubleValue();
                } catch (Exception e) {
                    if (firstNum.doubleValue() == 0) {
                        txtDisplay.setText("ERROR");
                        reset = true;
                        return;
                    }
                    res = 1 / firstNum.doubleValue();
                }
                txtDisplay.setText(res + "");
                reset = true;
            }
        });
    }

    private void pressChange() {
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (txtDisplay.getText().equals("0")) {
                    txtDisplay.setText("-0");
                } else if (txtDisplay.getText().equals("-0")) {
                    txtDisplay.setText("0");
                } else {
                    BigDecimal res = new BigDecimal("0");
                    try {
                        res = getNumber().multiply(BigDecimal.valueOf(-1));
                    } catch (Exception e) {
                        res = firstNum.multiply(BigDecimal.valueOf(-1)).stripTrailingZeros();
                    }
                    txtDisplay.setText(res.toPlainString());
                }
                reset = true;
            }
        });
    }

    private void pressMPlus() {
        btnMPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (getNumber().doubleValue() != 0) {
                    btnMR.setEnabled(true);
                    try {
                        memory = memory.add(getNumber());
                    } catch (Exception e) {
                        return;
                    }
                }
                reset = true;
            }
        });
    }

    private void pressMR() {
        btnMR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDisplay.setText(memory.toPlainString());
                reset = true;
            }
        });
    }

    private void pressMC() {
        btnMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memory = new BigDecimal("0");
                btnMR.setEnabled(false);
                reset = true;
            }
        });

    }

    private void pressMSub() {
        btnMSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (getNumber().doubleValue() != 0) {
                    btnMR.setEnabled(true);
                    try {
                        memory = memory.subtract(getNumber());
                    } catch (Exception e) {
                        return;
                    }
                }
                reset = true;
            }
        });
    }
}
