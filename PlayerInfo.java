import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PlayerInfo extends JDialog {

    public Player player;
    private JTextField tfname1;
    private JTextField tfname2;
    private JPanel Player_Panel;

    private JButton startGameButton;
    private JTextField enterNameTextField;
    private JTextField PLAYER1TextField;


    public PlayerInfo(JFrame parent) {
        super(parent);
        tfname1 = enterNameTextField;
        tfname2 = enterNameTextField;

        setTitle("Enter Details");
        setContentPane(Player_Panel);
        setMinimumSize(new Dimension(600, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name1 = tfname1.getText();
                String name2 = tfname2.getText();

                if(name1.isEmpty() || name2.isEmpty()){
                    JOptionPane.showMessageDialog(Player_Panel,
                            "Please Enter Player Name",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Player player = addUserToDatabase(name1,name2);
                if(player == null){
                    JOptionPane.showMessageDialog(Player_Panel,
                            "Error adding player to database.",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ticTacToe game = new ticTacToe();
                game.setVisible(true);

                dispose();
            }
        });
        setVisible(true);
    }

    private Player addUserToDatabase(String name1, String name2) {
        Player player = null;
        final String DB_URl="jdbc:mysql://localhost:3306/Juggernaut";
        final String USERNAME="root";
        final String PASSWORD="112908@Saumit";

        try (Connection connection = DriverManager.getConnection(DB_URl,USERNAME,PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO players(name1,name2)" +
                             " VALUES(?,?)")) {

            preparedStatement.setString(1,name1);
            preparedStatement.setString(2,name2);
            //Insert Row into the table
            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                player = new Player();
                player.setName1(name1);
                player.setName2(name2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return player;
    }
}
