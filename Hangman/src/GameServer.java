//Brett Haugh & Shawn Harriett
//CMSCI 355 A
//Project 2

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.File;

public class GameServer {
    public static void main(String[] args) throws Exception {
        /*
        First, we have to define the protocol that the two programs will use. The server begins by
        setting up a welcoming socket on port 6789 using TCP. The client then “knocks on the
        door” to establish a connection. 
        */
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Server connected. The game is now live.");

        File file = new File("WordBank.txt");
        Word secretWord = new Word(new Scanner(file));
        Socket serverSocket = welcomeSocket.accept(); //creates a connection to client
        DataOutputStream sendSecretWord = new DataOutputStream(serverSocket.getOutputStream());
        sendSecretWord.writeBytes(secretWord.getLength()+"\n");
        System.out.println("Word Length: "+secretWord.getLength());
        Scanner inFromClient = new Scanner(serverSocket.getInputStream());
        
        while(true) {
            String message = inFromClient.nextLine(); //read message from client
            DataOutputStream outToClient = new DataOutputStream(serverSocket.getOutputStream());
            if(message.equals("You Win!")){
                //close sockets & streams
                serverSocket.close();
                inFromClient.close();
                outToClient.close();
            }
            else if(message.equals("You Lose!")){
                System.out.println("The word was "+secretWord.getWord());
                //close sockets & streams
                serverSocket.close();
                inFromClient.close();
                outToClient.close();
            }
            else{
            System.out.println("Message recieved: "+message+"\n");
            char guessLtr = message.charAt(0);
            String resultOfGuess = secretWord.getSpots(guessLtr); 
            outToClient.writeBytes(resultOfGuess+"\n");
            }
            
        }

    }
}
