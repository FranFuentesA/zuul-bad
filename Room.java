import java.util.HashMap;
import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room surEsteExit;
    private Room noroEsteExit;
    private HashMap<String, Room> salas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salas = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direccion, Room localizacion) 
    {
        salas.put(direccion,localizacion);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     *  Metodo que devuelve un objeto tipo Room comparando con la cadena 
     */
    public Room getExit(String direction){
        Room devolver = null;
        devolver = salas.get(direction);
        return devolver;
    }

    /**
     * Metodo que devulve la informacion de las salidas
     */
    public String getExitString()
    {
         String salida = "Salida ";
        if(salas.get("north") != null) {
            salida += " north ";
        }
        if(salas.get("east") != null) {
            salida += " east ";
        }
        if(salas.get("south") != null) {
            salida += " south";
        }
        if(salas.get("west") != null) {
            salida += " west ";
        }
        if(salas.get("sureste") != null) {
            salida += " sureste ";
        }
        if(salas.get("noroeste") != null) {
            salida += " noroeste ";
        }
        return salida;
    }

  /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "Tu estas en " + getDescription() + " Salidas: " + getExitString();
    }
}
