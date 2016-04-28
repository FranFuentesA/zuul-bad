import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> camino;
    private Room habitacionAnterior;
    private Game game;
    private ArrayList<Item> lObjetos;
    private float carga;
    private float pesoObjetos = 0;;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        lObjetos = new ArrayList<>();
        this.carga = carga;
        this.currentRoom = currentRoom;
        camino = new Stack<>();
        pesoObjetos = 0;
    }

    public void fijarHabitacion(Room habitacion){
        if(currentRoom != null){
            camino.push(currentRoom);
        }
        currentRoom = habitacion;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("No hay puerta, intente ir en otra direccion");
        }
        else {
            camino.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());  
            System.out.println();            
        }
    }

    /**
     * 
     * Metodo que muestra por pantalla la informacion de la localizacion
     */
    public void printLocationInfo() 
    {
        System.out.println(currentRoom.getLongDescription()); 
        System.out.println();
    }

    public void atras()
    {
        if(!camino.empty()){
            currentRoom = camino.pop();
        } else {
            System.out.println("No puedes ir haca atras");
        }   
    }

    /**
     * Metodo que añade un objeto al inventario
     */
    public void cogeObjeto(String descripcion){
        Item item = currentRoom.buscaObj(descripcion);
        //50 es el peso maximo que puede llevar el jugador
        if(item != null && (pesoObjetos + item.getPesoKg()) < 50 && item.infoCoger() == true){
            lObjetos.add(item);
            pesoObjetos += item.getPesoKg();
            currentRoom.elimnaObj(item);
        }
        else{
            System.out.println("Buff, no puedo coger esto");
        }
    }

    /**
     * Metodo que tira el objeto en la habitacion
     */
    public void tiraObjeto(String descripcion){
        int i = 0;
        boolean encontrado = false;
        while(i < lObjetos.size() && !encontrado){
            if(lObjetos.get(i).getDescripcionObjeto().equals(descripcion)){
                currentRoom.addItem(lObjetos.get(i));
                carga -= lObjetos.get(i).getPesoKg();
                lObjetos.remove(lObjetos.get(i));
                encontrado = true;
                System.out.println("he tirado el objeto");
            }
            i++;
        }
        if(!encontrado) {
            System.out.println("Tengo el inventario vacio");
        }
    }
}
