import java.util.HashMap;
import java.util.ArrayList;

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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> salas;
    private ArrayList<Item> objetos;
   

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
        objetos = new ArrayList<>();
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
        String salida = "Salidas ";
        for (String clave : salas.keySet()) {
            salida += clave + " - ";
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
        String devolver = "";
        devolver = "Estas en " + getDescription() + " "+ getExitString();
        if(objetos.size()== 0){
              devolver += "No puedo ver ningun objeto util en la sala";
         }
         else{
              devolver += "En la sala hay: ";
             for(Item lObjetos : objetos){
                 devolver += "\n" + lObjetos.getDescripcionObjeto() + " Peso: " + lObjetos.getPesoKg();
             }
         }
         return devolver;
    }
     /**
      * Metodo que añade objetos a las salas
      */
     public void addItem(Item objeto){
         objetos.add(objeto);
     }
             
     /**
      * Metodo que busca los objetos en la habitacion
     */
     public Item buscaObj(String descripcion){
        int i = 0;
        boolean encontrado = false;
        Item item = null;
        while(i < objetos.size() && !encontrado){
            if(objetos.get(i).getDescripcionObjeto().equals(descripcion)){
                 item = objetos.get(i);
                 encontrado = true;
            }
             i++;
         }
         return item;
     }
    
     /**
      * Meotodo que elimina un objeto cuando lo coge un jugador
      */
    public void elimnaObj(Item item){
         int i = 0;
         boolean encontrado = false;
         while(i < objetos.size() && !encontrado){
            if(objetos.get(i).getDescripcionObjeto().equals(item.getDescripcionObjeto())){
                 objetos.remove(objetos.get(i));
                 encontrado = true;
            }
             i++;
         }
    }
}
    

