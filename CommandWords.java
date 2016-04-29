import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private HashMap<String, Option> comandos;
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        comandos = new HashMap<>();
        comandos.put("go", Option.GO);
        comandos.put("quit", Option.QUIT);
        comandos.put("help", Option.HELP);
        comandos.put("look", Option.LOOK);
        comandos.put("eat", Option.EAT);
        comandos.put("back", Option.BACK);
        comandos.put("take", Option.TAKE);
        comandos.put("drop", Option.DROP);
        comandos.put("items", Option.ITEMS);
        comandos.put("uknown", Option.UNKNOWN);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean devolver = comandos.containsKey(aString);
        return devolver;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        System.out.println("Los comandos disponibles son:");
        for(String comando : comandos.keySet()) {
            System.out.print(" "+comando+" ");
        }
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        Option opcion = Option.UNKNOWN; 
        if (opcion != null) {
            opcion = comandos.get(commandWord);    
        }
        return opcion;
    }
}

