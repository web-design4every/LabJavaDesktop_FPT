package Controller;

import GUI.gui;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Control {

    gui puzzle = new gui();
    private int moveCount = 0;
    private int moveAccount = 0;
    private Timer timer;
    private JButton[][] matrix;
    private boolean isGameStart = false;
    private boolean isGameEnd;
    int size = 0;
    boolean first = true;

    public Control() {
        newGame();
        puzzle.setLocationRelativeTo(null);
        puzzle.setVisible(true);
        isGameEnd = false;
    }

    public void init() {
        this.initMoveCount();
        this.initTimeCount();
        this.initMatrix();
        isGameStart = true;
        isGameEnd = false;
    }

    public void initMoveCount() {
        moveCount = 0;
        puzzle.lblCountMove.setText("0");
    }

    public void initTimeCount() {
        puzzle.lblCountTime.setText("0");
        timer = new Timer(1000, new ActionListener() {
            int second = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                second++;
                puzzle.lblCountTime.setText(String.valueOf(second));
            }
        });
        timer.start();
    }

    public void initMatrix() {
//        cach 1: lấy index trong comboBox + 3 // vi index ban đầu = 0
//        size = puzzle.cbxSize.getSelectedIndex() + 3;
//        cach 2: lấy item -- > split lấy a[0].
        if (first) {
            isGameStart = true;
            size = 3;
            initTimeCount();
            initMoveCount();
            isGameEnd = false;
            first = false;
        } else {
            String value = puzzle.cbxSize.getSelectedItem() + "";
            String[] arr = value.split("x");
            size = Integer.parseInt(arr[0]);
        }
        //
        puzzle.pnLayout.removeAll();//remove before add new
        puzzle.pnLayout.setLayout(new GridLayout(size, size, 10, 10));
        puzzle.pnLayout.setPreferredSize(new Dimension(60 * size, 60 * size));
        // get value it size  3x3 4x4 5x5
        matrix = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton(i * size + j + 1 + "");   //0 0 -> 1//0 1 ->2 //0-2 --> 3
                matrix[i][j] = button;
                puzzle.pnLayout.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                                                  // kiem tra button co move sang duoc o trong ko 
                            if (checkMove(button)) {
                                moveButton(button);
                                if (checkWin()) {
                                    isGameStart = false;
                                    JOptionPane.showMessageDialog(null, "You won!");
                                    int confirm = JOptionPane.showConfirmDialog(null, "Do you want to start"
                                            + " new game?", "New Game", JOptionPane.YES_NO_OPTION);
                                    timer.stop();
                                    if (confirm == JOptionPane.YES_OPTION) {
                                        first = true;
                                        initMatrix();
                                    } else if (confirm == JOptionPane.NO_OPTION) {
                                        for(int i=0; i<size; i++){
                                            for(int j=0; j<size; j++){
                                                matrix[i][j].setEnabled(false);
                                            }
                                        }
                                        
                                        isGameEnd = true;
                                                
                                    }
                                    
                                    
                                }
                            }
                      
                    }
                });
            }
        }
        matrix[size - 1][size - 1].setText(""); // tạo 1 ô trống
        randomMatrix();
        puzzle.setResizable(false);
        puzzle.pack();
    }

    public void randomMatrix() {
        Random rd = new Random();
        for (int i = 0; i < 1000; i++) {
            // lay vi tri button rong
            Point p = getPositionOfEmptyButton(); //tim vi tri cua rong
            int number = rd.nextInt(4);  //random ra tu 0 -3
            switch (number) {
                case 0: // tren
                    if (p.x > 0) {
                        matrix[p.x][p.y].setText(matrix[p.x - 1][p.y].getText());
                        matrix[p.x - 1][p.y].setText("");
                    }
                    break;
                case 1: // phai
                    if (p.y < size - 1) {
                        matrix[p.x][p.y].setText(matrix[p.x][p.y + 1].getText());
                        matrix[p.x][p.y + 1].setText("");
                    }
                    break;
                case 2: // duoi
                    if (p.x < size - 1) {
                        matrix[p.x][p.y].setText(matrix[p.x + 1][p.y].getText());
                        matrix[p.x + 1][p.y].setText("");
                    }
                    break;
                case 3: //trai
                    if (p.y > 0) {
                        matrix[p.x][p.y].setText(matrix[p.x][p.y - 1].getText());
                        matrix[p.x][p.y - 1].setText("");
                    }
                    break;
            }
        }
    }

    public boolean checkWin() {
        // condition first must be true when last button is empty 
        if (matrix[size - 1][size - 1].getText().equals("")) {
            // run from the first insex to the last index row of matrix
            for (int i = 0; i < size; i++) {
                // run from the first insex to the last index column of matrix
                for (int j = 0; j < size; j++) {
                    // condition check index at last matrix also user win 
                    if (i == size - 1 && j == size - 1) {
                        return true;
                    }
                    // condition check index one button of all button into matrix is wrong original rule will fail
                    if (!matrix[i][j].getText().equals(i * size + j + 1 + "")) {
                        return false;
                    }
                }
            }
            return true;
        }
        // when if wrong will return false;
        return false;
    }

    public void moveButton(JButton button) {
        Point p = getPositionOfEmptyButton(); //lay vi tri cua button rong
        matrix[p.x][p.y].setText(button.getText());
        button.setText("");
        moveCount++;
        puzzle.lblCountMove.setText(String.valueOf(moveCount));
    }

    public boolean checkMove(JButton button) {
        if (button.getText().equals("")) {
            return false;
        }
        //get Index is Empty
        Point p = getPositionOfEmptyButton();
        Point clickedPoint = null;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // get index Button when user clicked
                if (matrix[i][j].getText().equals(button.getText())) {
                    clickedPoint = new Point(i, j);
                }
            }
        }

        if (p.x == clickedPoint.x && Math.abs(p.y - clickedPoint.y) == 1) {
            return true;
        }
        if (p.y == clickedPoint.y && Math.abs(p.x - clickedPoint.x) == 1) {
            return true;
        }
        return false;
    }

    public Point getPositionOfEmptyButton() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // find out index button is Empty
                if (matrix[i][j].getText().equals("")) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    // 1 2 3 4 5 8 6 7 error --> ok
    // // 0=yes, 1=no, 2=cancel
    public void newGame() {
        this.initMatrix();

        puzzle.getBtnNewGame().addActionListener((e) -> {

            if (isGameStart){
                timer.stop();
                int confirm = JOptionPane.showConfirmDialog(null, "Do you must"
                        + " be want to make new game?", "New Game", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    this.init();
                } else if (confirm == JOptionPane.NO_OPTION) {
                    timer.start();
                }
            } 
            else if(isGameEnd){
                int confirm = JOptionPane.showConfirmDialog(null, "Do you must"
                        + " be want to make new game?", "New Game", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    this.init();
                }
            }else {
                this.init();
            }
        });
    }
}
