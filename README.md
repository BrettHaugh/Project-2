# TCP Hangman Project

## Created by Brett Haugh & Shawn Harriett

This is a version of the game "Hangman" that uses a TCP server to allow users to connect to the client. The GUI and Word Java classes were provided by our professor. Our job was to implement a server and client that would A) allow for players to connect and play  B) utilize the classes give to us to create a user-friendly experience.

## Server.java Responsibilites
- Initializing TCP sockets
- Initializing the word and sending starting message
- Recognizing and responding to letter guesses from client
- Recognizing and responding to won/lost message from client
## Client.java Responsibilites
- Contacting server and obtaining/displaying hostname IP address
- Creating and sending letter guesses to the server
- Processing list of positions of a letter
- Continuing play if the conditions for the game ending have not been met
- Recognizing and processing win and sending it to the server
- Recognizing and processing loss and sending it to the server


## Folder Structure

## Class Breakdown

## Coding Implementation

