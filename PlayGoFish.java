package com.PersonalProjects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PlayGoFish {
    private static LinkedList<String> playerCards;
    private static LinkedList<String> dealerCards;
    private static boolean foundCard;
    public static void main(String[] args) {
        //make deck of cards
        ArrayList<String> newDeck = Deck.makeDeck();
        playerCards = new LinkedList<>();
        dealerCards = new LinkedList<>();
        String playerCard, dealerCard;
        Scanner keyboard = new Scanner(System.in);

        //deal the player and dealer 7 cards each
        for (int i = 0; i < 7; i++) {
            playerCard = newDeck.get((int) (Math.random() * (newDeck.size())));
            playerCards.add(playerCard);
            newDeck.remove(playerCard);
            dealerCard = newDeck.get((int) (Math.random() * (newDeck.size())));
            dealerCards.add(dealerCard);
            newDeck.remove(dealerCard);
        }

        //show players cards before and after eliminating pairs
        System.out.println("PLAYER CARDS: "+ playerCards);
        System.out.println("After getting rid of pairs: ");
        //call to method to eliminate pairs
        playerCards = GoFishGamePlay.differentCards(playerCards);
        System.out.println("PLAYER CARDS: "+playerCards);

        //eliminate pairs from dealers cards
        dealerCards = GoFishGamePlay.differentCards(dealerCards);

        //start the game
        while(true) {
            //dealer asking player for cards
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("DEALER'S TURN: ");
            do {
                //choosing random card from dealer to ask for
                String randomCard;
                int index = (int) (Math.random() * dealerCards.size());
                randomCard = dealerCards.get(index);
                String s[] = randomCard.split(" ");
                System.out.println("Do you have any " + s[0] + "'s?");
                //method to check if player has the card
                checkForCard(s[0], playerCards, dealerCards);
                //eliminate pairs if necessary
                dealerCards = GoFishGamePlay.differentCards(dealerCards);
                //check if either player is out of cards
                isGameOver();
            //dealer keeps asking for cards if the player had the previous card asked for
            } while (foundCard);


            System.out.println("No.");
            System.out.println("PLAYER CARDS: "+ playerCards);
            //add card to dealer after no match with player
            dealerCard = newDeck.get((int) (Math.random() * (newDeck.size())));
            newDeck.remove(dealerCard);
            dealerCards.add(dealerCard);
            //eliminate pairs if necessary
            dealerCards = GoFishGamePlay.differentCards(dealerCards);
            //check if either player is out of cards
            isGameOver();

            //player asking dealer for cards
            System.out.println("----------------------------------------------------------------------------");
            do {
                System.out.println("PLAYER'S TURN: ");
                //ask player what card they want to ask for
                System.out.println("Enter the card you would like to ask the dealer if they have (Capitalize first letter if word): ");
                String card = keyboard.nextLine();
                //check if dealer has card
                checkForCard(card, dealerCards, playerCards);
                playerCards = GoFishGamePlay.differentCards(playerCards);
                isGameOver();
            } while (foundCard);
            System.out.println("The dealer does not have the card.");
            //add card to player if no match with dealer
            playerCard = newDeck.get((int) (Math.random() * (newDeck.size())));
            newDeck.remove(playerCard);
            playerCards.add(playerCard);
            System.out.println("Card drawn from deck: "+playerCard);
            playerCards = GoFishGamePlay.differentCards(playerCards);
            System.out.println("PLAYER CARDS: "+playerCards);
            isGameOver();
        }
    }

    //method to check if card asked for is there
    private static void checkForCard(String card, LinkedList<String> cards,LinkedList<String> cards2){
        String[] s;
        for(int i=0;i< cards.size();i++){
            s=cards.get(i).split(" ");
            if(card.equalsIgnoreCase(s[0])){
                System.out.println("Card acquired: "+cards.get(i));
                //add card to one who asked
                cards2.add(cards.get(i));
                //remove card from other
                cards.remove(cards.get(i));
                //stay in loop of asking for card if the card was there
                foundCard=true;
                break;
            }
            else
                foundCard=false;
        }
    }

    //end game if either the player or dealer are out of cards
    private static void isGameOver(){
        if(playerCards.size()==0){
            System.out.println("Congratulations, you won.");
            System.out.println("Game Over.");
            System.exit(1);
        }
        if(dealerCards.size()==0){
            System.out.println("The dealer won.");
            System.out.println("Game Over.");
            System.exit(1);
        }
    }
}