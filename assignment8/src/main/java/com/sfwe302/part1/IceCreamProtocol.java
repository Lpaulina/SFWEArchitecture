package com.sfwe302.part1;

import java.util.List;
import java.util.Map;

public class IceCreamProtocol {
    private static final int START = -1;
    private static final int WAITING = 0;
    private static final int SENT_PRICE = 1;

    private static Map<String, Integer> costs = Map.of("vanilla", 2, "chocolate", 2, "lemon", 1);
    private static List<String> iceCreamFlavors = List.of("vanilla", "chocolate", "lemon");
    private static int change;

    private static String GREETING = "Hey, what Ice Cream can I offer?";


    private static int state = START;

    private Integer cost;

    public String processInput(String input) {
        String output = null;

        if (state == START) {
            output = GREETING;
            state = WAITING;
        } 
        
        else if (state == WAITING) {
            if (!iceCreamFlavors.contains(input.toLowerCase())) {
                state = START;
                output = "We don't have that flavor ... restarting";
            } else {
                cost = costs.get(input);
                output = "$" + cost;
                state = SENT_PRICE;
            }
        } else if (state == SENT_PRICE) {
            String numberStr = input.replaceAll("[^0-9]", "");
            Integer payment;

            try {
                payment = Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                state = WAITING;
                return "Protocol not followed ...restarting \n"+GREETING;
            }

            if (payment == cost) {
                output = "great you have an exact \n Here you are! Thank you\n"+ GREETING;
                state = WAITING;
            } else if (payment > cost) {
                change = payment -cost;
                output = "Here $" + change + " back \n  Here you are! Thank you \n"+ GREETING;
                state = WAITING;
            } else if (payment < cost){
                output = "not enough, $" + cost + " please";
            }

        }
        return output;
    }

}
