import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class NewClient {
    public static void main(String[] args) throws Exception {
        //Contacting server (including obtaining hostname)
        Scanner userInput = new Scanner(System.in);
        Socket clientSocket = new Socket("localhost", 6789);
        System.out.println("Guess a letter: ");
        
        //Creating and sending letter message
        String guessLtr = userInput.nextLine();
        //int count = 0;

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(guessLtr+"\n");

        //Processing list of positions of a letter
        Scanner responseFromServer = new Scanner(clientSocket.getInputStream());
        String correctness = responseFromServer.nextLine();
        System.out.println(correctness);

        Scanner nextResponseFromServer = new Scanner(clientSocket.getInputStream());
        String wordProgress = nextResponseFromServer.nextLine();
        System.out.println(wordProgress);


        clientSocket.close();
        outToServer.close();
        responseFromServer.close();
        userInput.close();
    }
}
