
public class Main {
    public static void main(String[] args) {

        PlayerInfo pi = new PlayerInfo(null);
        Player player=pi.player;

        if(player!=null){
            System.out.println("Successfully Added"+player.name1+player.name2);
        }
        else{
            System.out.println("Cancelled");
        }
        ticTacToe game = new ticTacToe();


    }

}