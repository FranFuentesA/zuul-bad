import java.util.HashMap;
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
    public void setExits(Room north, Room east, Room south, Room west, Room sureste, Room noroeste) 
    {
        if(north != null)
         
            northExit = north;
            salas.put("north", north);
        
        if(east != null)
                   
            eastExit = east;
            salas.put("east", east);
        
        if(south != null)
        
            southExit = south;
            salas.put("south", south);
        
        if(west != null)
         
            westExit = west;
            salas.put("west", west);
        
        if(sureste != null)
          
            surEsteExit = sureste;
            salas.put("sureste", sureste);
        
        if(noroeste != null)
           
            noroEsteExit = noroeste;
            salas.put("noroeste", noroeste);
        
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
        
        if(direction.equals("north")){
            devolver = salas.get("north");
        }
        if(direction.equals("east")){
            devolver = salas.get("east");
        }
        if(direction.equals("south")){
            devolver = salas.get("south");
        }
        if(direction.equals("west")){
            devolver = salas.get("west");
        }
        if(direction.equals("sureste")){
            devolver = salas.get("sureste");
        }
        if(direction.equals("noroeste")){
            devolver = salas.get("noroeste");
        }
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

}
