import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ticTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();

    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];

    JButton resetButton=new JButton("Restart");
    boolean player1_turn;


    ticTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());




        textfield.setBackground(new Color(175, 238, 238));
        textfield.setForeground(new Color(0, 128, 128));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("JUGGERNAUT");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 10);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(95, 158, 160));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("My Bola", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }
        resetButton.setFont(new Font("My Bola", Font.BOLD, 40));
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> resetGame());

        JPanel buttonPanelWithReset = new JPanel(new BorderLayout());
        buttonPanelWithReset.add(button_panel, BorderLayout.CENTER);
        buttonPanelWithReset.add(resetButton, BorderLayout.SOUTH);

        title_panel.add(textfield, BorderLayout.NORTH);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(buttonPanelWithReset, BorderLayout.CENTER);

        firstTurn();
        frame.setVisible(true);



    }

    private void resetGame() {
        // Reset the text on all buttons to be empty
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }

        // Set the player turn to the initial value (player 1's turn)
        player1_turn = true;

        // Set the text on the title label to indicate player 1's turn
        textfield.setText("X turn");

        enableButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(135, 206, 250));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("X turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(65, 105, 225));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("O turn");
                        check();

                    }

                }
            }
        }
        boolean allButtonsClicked=true;
        for(int i=0;i<9;i++){
            if(buttons[i].getText()==""){
                allButtonsClicked=false;
                break;
            }
        }
        if (allButtonsClicked) {
            int option = JOptionPane.showConfirmDialog(frame, "All buttons filled. Do you want to restart the game?");
            if (option == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }
    public void disableButtons() {
        for (int b=0 ; b<9 ; b++) {
            buttons[b].setEnabled(false) ;
        }
    }

    // game starts .
    public void enableButtons() {
        for (int b=0 ; b<9 ; b++) {
            buttons[b].setEnabled(true) ;
        }
    }

    public void firstTurn() {


        disableButtons() ;
// Wait for 2s before start .
        try {
            Thread.sleep(2000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
// restore the buttons .
        enableButtons() ;

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }

    }

    public void check() {
        if (
                (buttons[0].getText()== "X") &&
                        (buttons[1].getText() == "X") &&
                        (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
        }
        if (
                (buttons[3].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
        }
        if (
                (buttons[6].getText() == "X") &&
                        (buttons[7].getText() == "X") &&
                        (buttons[8].getText() == "X")) {
            xWins(7, 8, 9);
        }
        if (
                (buttons[0].getText() == "X") &&
                        (buttons[3].getText() == "X") &&
                        (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
        }
        if (
                (buttons[1].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
        }
        if (
                (buttons[2].getText() == "X") &&
                        (buttons[5].getText() == "X") &&
                        (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
        }
        if (
                (buttons[0].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
        }
        if (
                (buttons[2].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
        }
        if(
                (buttons[0].getText()=="O")&&
                        (buttons[1].getText()=="O")&&
                        (buttons[2].getText()=="O")){
            oWins(0,1,2);
        }
        if(
                (buttons[3].getText()=="O")&&
                        (buttons[4].getText()=="O")&&
                        (buttons[5].getText()=="O")){
            xWins(3,4,5);
        }
        if(
                (buttons[6].getText()=="O")&&
                        (buttons[7].getText()=="O")&&
                        (buttons[8].getText()=="O")){
            oWins(7,8,9);
        }
        if(
                (buttons[0].getText()=="O")&&
                        (buttons[3].getText()=="O")&&
                        (buttons[6].getText()=="O")){
            oWins(0,3,6);
        }
        if(
                (buttons[1].getText()=="O")&&
                        (buttons[4].getText()=="O")&&
                        (buttons[7].getText()=="O")){
            oWins(1,4,7);
        }
        if(
                (buttons[2].getText()=="O")&&
                        (buttons[5].getText()=="O")&&
                        (buttons[8].getText()=="O")){
            oWins(2,5,8);
        }
        if(
                (buttons[0].getText()=="O")&&
                        (buttons[4].getText()=="O")&&
                        (buttons[8].getText()=="O")){
            oWins(0,4,8);
        }
        if(
                (buttons[2].getText()=="O")&&
                        (buttons[4].getText()=="O")&&
                        (buttons[6].getText()=="O")){
            oWins(2,4,6);
        }
        // check tie condition
        for (int x=0 ; x<9 ; x++) {
            // if 1 of 9 button texts is blank
            if (buttons[x].getText().isBlank()) {
                // the tie condition is not satisfied .
                break ;
            }
            // if both 9 button texts are not blank .
            if (x == 8) {
                // game finishes .
                // the text color of buttons will be disabled as well, so they will turn gray .
                disableButtons() ;
                textfield.setText("Draw") ;
            }
        }

    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0 ; i<9;i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins");


    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0 ; i<9;i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins");


    }


    public void setVisible(boolean visible) {
    }
}