
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mr Duc Anh
 */
public class GuiController {

    private GuiView gui;
    private int size = 0;
    private JButton[][] matrix;
    private int moveCount = 0;
    private Thread timer;
    private boolean isPlaying;
    private int time = 0;

    public GuiController() {
        gui = new GuiView();
        gui.setVisible(true);
        gui.setResizable(false);
        gui.setTitle("Puzzle Game");
        startGame();
    }

    public void startGame() {

        gui.getBtnNew().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPlaying = true;
                addButton();
                gamePlay();
            }
        });
    }

    public void gamePlay() {
        timer = new Thread(new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (isPlaying) {
                        gui.getLblTime().setText("Elapsed " + (time++) + "s");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        });
        timer.start();
    }

    public void addButton() {
        gui.getPzPanel().removeAll();
        String text = gui.getCboSize().getSelectedItem().toString();
        String[] s = text.split("x");
//        System.out.println(s[0]);
        size = Integer.parseInt(s[0]);
        gui.getPzPanel().setLayout(new GridLayout(size, size, 15, 15));
        gui.getPzPanel().setPreferredSize(new Dimension(size * 60, size * 60));
        matrix = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton btn = new JButton(i * size + j + 1 + "");
                matrix[i][j] = btn;
                gui.getPzPanel().add(btn);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (checkMoveButton(btn)) {
                            moveButton(btn);
                            if (checkWin()) {
                                timer.stop();
                                time = 0;
                                moveCount = 0;
                                isPlaying = false;
                                JOptionPane.showMessageDialog(gui, "You win");
                                gui.getLblCount().setText("Move count: 0");
                                gui.getLblTime().setText("Elapsed: 0s");
                            }
                        }
                    }
                });
            }
        }
        matrix[size - 1][size - 1].setText("");
        mixButton();
        gui.pack();
    }

    public Point getEmptyPosition() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getText().equals("")) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public void mixButton() {
        for (int k = 0; k < 10; k++) {
            Point p = getEmptyPosition();
            int i = p.x;
            int j = p.y;
            Random r = new Random();
            int choice = r.nextInt(4);
            switch (choice) {
                case 0: // move up
                    if (i > 0) {
                        String text = matrix[i - 1][j].getText();
                        matrix[i][j].setText(text);
                        matrix[i - 1][j].setText("");
                    }
                    break;
                case 1: // move down
                    if (i < 2) {
                        String text = matrix[i + 1][j].getText();
                        matrix[i][j].setText(text);
                        matrix[i + 1][j].setText("");
                    }
                    break;
                case 2: // move left
                    if (j > 0) {
                        String text = matrix[i][j - 1].getText();
                        matrix[i][j].setText(text);
                        matrix[i][j - 1].setText("");
                    }
                    break;
                case 3: // move right
                    if (j < 2) {
                        String text = matrix[i][j + 1].getText();
                        matrix[i][j].setText(text);
                        matrix[i][j + 1].setText("");
                    }
                    break;
            }
        }
    }

    public boolean checkMoveButton(JButton btn) {
        Point p = getEmptyPosition();
        int i1 = 0;
        int j1 = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (btn.getText().equals(matrix[i][j].getText())) {
                    i1 = i;
                    j1 = j;
                    break;
                }
            }
        }
        if (p.x == i1 && Math.abs(p.y - j1) == 1) {
            return true;
        }
        if (p.y == j1 && Math.abs(p.x - i1) == 1) {
            return true;
        }
        return false;
    }

    public void moveButton(JButton btn) {
        Point p = getEmptyPosition();
        String pos = btn.getText();
        matrix[p.x][p.y].setText(pos);
        btn.setText("");
        moveCount++;
        gui.getLblCount().setText("Move count: " + moveCount + "");
    }

    public boolean checkWin() {
        if (matrix[size - 1][size - 1].getText().equals("")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - 1; j++) {
                    if (!matrix[i][j].getText().equals(i * size + j + 1 + "")) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
        
    }

    public boolean isWin() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length - 1; i++) {
                int el = i * size + j + 1;

                if (matrix[i][j].getText().equals("") || !matrix[i][j].getText().equals(el + "")) {
                    return false;
                }
            }
        }
        return true;
    }
}
