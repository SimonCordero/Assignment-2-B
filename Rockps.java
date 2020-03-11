import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
* Author: Simon Cordero.
* Last updated: 2020/03/11
**/ 
 
public class Rockps {
  public enum Item {
    ROCK, PAPER, SCISSORS,;
    public List<Item> losesToList;
    public boolean losesTo(Item other) {
      return losesToList.contains(other);
      
    }
    
    static {
      SCISSORS.losesToList = Arrays.asList(ROCK);
      ROCK.losesToList = Arrays.asList(PAPER);
      PAPER.losesToList = Arrays.asList(SCISSORS);
      
    }
    
  }
  
  public final Map<Item, Integer> counts = new EnumMap<Item, Integer>(Item.class) {{
      for(Item item:Item.values()) {
        put(item, 1);
      
      }
    
    }};
  
  private int totalThrows = Item.values().length;
  
  /**
  * This program allows the user to play RPS.
  */
  
  public static void main(String[] args) {
    Rockps rps = new Rockps();
    rps.run();
    
  }
  
  /**
  * Computer choice.
  **/ 
  
  public void run() {
    Scanner in = new Scanner(System.in);
    System.out.print("Let's play rock, paper scissors!" + "\n" + "Choice: ");
    while (in.hasNextLine()) {
      
      String input = in.nextLine();
      Item choice;
      try {
        choice = Item.valueOf(input.toUpperCase());
        
      } catch (IllegalArgumentException ex) {
        System.out.println("Invalid choice");
        continue;
        
      }
      Item aiChoice = getAiChoice();
      counts.put(choice, counts.get(choice) + 1);
      totalThrows++;
      System.out.println("Computer chose: " + aiChoice);
      if (aiChoice == choice) {
        System.out.println("Tie!");
        
      } else if (aiChoice.losesTo(choice)) {
        System.out.println("Nice! You win!");
        
      } else {
        System.out.println("Too bad! You lose!");
        
      }
      System.out.print("Choice: ");
      
    }
    
  }
  
  private static final Random rng = new Random();
  
  private Item getAiChoice() {
    int rand = rng.nextInt(totalThrows);
    for (Map.Entry<Item, Integer> entry:counts.entrySet()) {
      Item item = entry.getKey();
      int count = entry.getValue();
      if (rand < count) {
        List<Item> losesTo = item.losesToList;
        return losesTo.get(rng.nextInt(losesTo.size()));
        
      }
      rand -= count;
      
    }
    return null;
    
  }
  
}