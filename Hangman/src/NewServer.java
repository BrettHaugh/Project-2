import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class NewServer {
    public static void main(String[] args) throws Exception {
        //Initializing sockets
        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket serverSocket = welcomeSocket.accept(); //creates a connection to client
        
        //Initializing word and sending starting message
        System.out.println("You wanna play a game?");
        
        String str = "test";
        int keyNum = 0;
        boolean correctness = false;
        
        Map<Integer, Character> word = new HashMap<>();
        for(int i=0; i<str.length(); i++){
            keyNum = i;
            word.put(keyNum, str.charAt(i));
        }
        //System.out.println(word);
        System.out.println(InetAddress.getLocalHost());
        
        ArrayList<String> hiddenWord = new ArrayList<>();
        for(int i=0; i<word.size(); i++){
            hiddenWord.add("*");
        }
        String tempString = hiddenWord.toString();
         
        //Recognizing and responding to letter message 
        while(true) {
            Scanner clientInput = new Scanner(serverSocket.getInputStream());
            String clientGuess = clientInput.nextLine(); //read message from client
            System.out.println("You Guessed: "+clientGuess);
            String updatedGuess = clientGuess.toLowerCase();
            
            DataOutputStream responseToGuess = new DataOutputStream(serverSocket.getOutputStream());
            DataOutputStream sendProgress = new DataOutputStream(serverSocket.getOutputStream());
            
            //System.out.println(updatedGuess.charAt(0));
            if(word.containsValue(updatedGuess.charAt(0))){
                correctness = true;
                for(int i=0; i<hiddenWord.size(); i++){
                    if(word.get(i) == updatedGuess.charAt(0)){
                        hiddenWord.add(i, updatedGuess);
                    }
                }
                tempString = hiddenWord.toString();
                responseToGuess.writeBytes("Correct");
            }
            else{
                correctness = false;
                responseToGuess.writeBytes("Incorrect");
            }
            System.out.println(tempString);
            responseToGuess.writeBytes("Correct");
            sendProgress.writeBytes(hiddenWord.toString());


            //close sockets & streams
            serverSocket.close();
            clientInput.close();
            
        }
        

    }
}
