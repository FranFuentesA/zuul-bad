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
        comandos.put(Option.GO.getComando(), Option.GO);
        comandos.put(Option.QUIT.getComando(), Option.QUIT);
        comandos.put(Option.HELP.getComando(), Option.HELP);
        comandos.put(Option.LOOK.getComando(), Option.LOOK);
        comandos.put(Option.EAT.getComando(), Option.EAT);
        comandos.put(Option.BACK.getComando(), Option.BACK);
        comandos.put(Option.TAKE.getComando(), Option.TAKE);
        comandos.put(Option.ITEMS.getComando(), Option.ITEMS);
        comandos.put(Option.DROP.getComando(), Option.DROP);
        comandos.put(Option.UNKNOWN.getComando(), Option.UNKNOWN);
        comandos.put(Option.POINTS.getComando(), Option.POINTS);
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

