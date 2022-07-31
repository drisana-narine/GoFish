package com.PersonalProjects;

import java.util.LinkedList;

public class GoFishGamePlay {
    //keep track of different types (9,10,Jack,etc.) of cards
    public static LinkedList<String> differentCards(LinkedList<String> cards){
        //linked list to keep track of different card types
        LinkedList<Integer> different = new LinkedList<>();
        for(int i = 0; i < cards.size(); i++){
            String[] s = cards.get(i).split(" ");
            //add card type to linked list if it is not already in there
            if(!different.contains(mapToNum(s[0]))){
                different.add(mapToNum(s[0]));
            }
        }
        return countAmounts(different,cards);
    }

    //amount of each type of card
    private static LinkedList<String> countAmounts (LinkedList<Integer> different,LinkedList<String> cards){
        //linked list to keep track of amounts of each card type
        LinkedList<Integer> amounts = new LinkedList<>();
        //create linked list the same size as linked list with card types
        for(int i = 0; i < different.size(); i++){
            amounts.add(0);
        }

        //go through cards and count hiw many of each type
        for(int j = 0; j < cards.size(); j++){
            String card = cards.get(j);
            String[] s = card.split(" ");
            int index = different.indexOf(mapToNum(s[0]));
            int amount = amounts.get(index);
            amounts.set(index, amount+1);
        }
        return findMultiples(different,amounts,cards);
    }

    //find card types to remove
    private static LinkedList<String> findMultiples(LinkedList<Integer> different,LinkedList<Integer> amounts,
                                                    LinkedList<String> cards){
        //linked list to keep track of which card types to remove
        LinkedList<Integer> numsToRemove = new LinkedList<>();
        for(int i = 0; i < amounts.size(); i++){
            //add card type to linked list of card types to remove if they occur 2 or 4 times
            if(amounts.get(i) == 2 || amounts.get(i) == 4){
                numsToRemove.add(different.get(i));
            }
            //remove two of the card type if it occurs 3 times
            if(amounts.get(i) == 3){
                int num = different.get(i);
                String s[];
                //variable to keep 1 out of 3 cards remove to put it back
                String keepCard=null;
                for(int j = 0; j < cards.size(); j++){
                    s = cards.get(j).split(" ");
                    if(mapToNum(s[0]) == num){
                        keepCard = cards.get(j);
                        cards.remove(cards.get(j));
                        j--;
                    }
                }
                cards.add(keepCard);
            }
        }
        return findRemoval(numsToRemove,cards);
    }

    //find cards to remove
    private static LinkedList<String> findRemoval(LinkedList<Integer> removalNums, LinkedList<String> cards){
        //linked list to keep track of cards to remove
        LinkedList<String> cardsToRemove = new LinkedList<>();
        for(int i = 0; i < cards.size(); i++){
            String[] s = cards.get(i).split(" ");
            if(removalNums.contains(mapToNum(s[0]))){
                cardsToRemove.add(cards.get(i));
            }
        }
        return removeCards(cards,cardsToRemove);
    }

    //remove cards
    private static LinkedList<String> removeCards(LinkedList<String> cards, LinkedList<String> cardsToRemove){
        for(int i = 0; i < cardsToRemove.size(); i++){
            cards.remove(cardsToRemove.get(i));
        }
        return cards;
    }

    //assigns card type a numerical value
    private static int mapToNum(String a){
        switch(a){
            case"Ace":
                return 1;
            case"2":
                return 2;
            case"3":
                return 3;
            case"4":
                return 4;
            case"5":
                return 5;
            case"6":
                return 6;
            case"7":
                return 7;
            case"8":
                return 8;
            case"9":
                return 9;
            case"10":
                return 10;
            case"Jack":
                return 11;
            case"Queen":
                return 12;
            case "King":
                return 13;
            default:
                return 0;
        }
    }
}
