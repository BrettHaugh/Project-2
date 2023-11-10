import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

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
        String[] index = resultFromServer.split(" ");

        if(resultFromServer == "") {
            gui.addMiss(resultFromServer);
            guessCounter += 1;
        }
        else {
            for(int i=0; i<index.length; i++){
                int newIndex = Integer.parseInt(index[i]);
                gui.addLetter(message.charAt(0), newIndex);
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
