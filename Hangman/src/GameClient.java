import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GameClient {
    public static void main(String[] args) throws Exception {

        
        Scanner inFromUser = new Scanner(System.in); //for user input
        Socket clientSocket = new Socket("localhost", 6789); //make connection to server
        System.out.println(InetAddress.getLocalHost()); //Print out IP address & hostname of connected server
        String win = "You Win!";
        String loss = "You Lose";
        int guessCounter = 0;
      

        Scanner inFromServer = new Scanner(clientSocket.getInputStream());
        GUI gui = new GUI(4);
        System.out.println("Enter your guess: ");

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        String message = inFromUser.nextLine();
        outToServer.writeBytes(message+"\n");
        
        String resultFromServer = inFromServer.nextLine();
        System.out.println(resultFromServer);
        StringTokenizer token = new StringTokenizer(resultFromServer, " ");
        System.out.println(token);
        int index = Integer.parseInt(token.nextToken());

        if(resultFromServer == "") {
            gui.addMiss(resultFromServer);
            guessCounter += 1;
        }
        else {
            while(token.hasMoreTokens()){
                gui.addLetter(message.charAt(0), index);
                System.out.println(token.countTokens());
            }
        }
        

        if(gui.isNotSolved() == false){
            if(guessCounter == 6){
                System.out.println(loss);
                outToServer.writeBytes(loss+"\n");
            }
            else{
                System.out.println(win);
                outToServer.writeBytes(win+"\n");
            }
        }

        clientSocket.close();
        outToServer.close();
        inFromServer.close();
        inFromUser.close();
    }
}
