import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> camino;
    private Player player;
    private Room habitacionAnterior;
    private int  contadorDeBack;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        camino = new Stack<>();
        contadorDeBack = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, oficinas, barracones, prision, almacen, control, armeria, torre;

        // create the rooms
        entrada = new Room("la entrada de la base");
        oficinas = new Room("la oficina de oficiales");
        barracones = new Room("los Barracones");
        prision = new Room("la Prision");
        almacen = new Room("almacen nuclear");
        control = new Room("la sala de control");
        armeria = new Room("la armeria");
        torre = new Room("la torre");
        // initialise room exits
        //norte, este, sur , oeste, sur este noroeste
        entrada.setExits("north",oficinas);
        oficinas.setExits("north",almacen);
        oficinas.setExits("east",armeria);
        oficinas.setExits("south",entrada);
        oficinas.setExits("west",barracones);
        barracones.setExits("east", oficinas);
        barracones.setExits("south",prision);
        prision.setExits("north",barracones);
        almacen.setExits("east", control);
        almacen.setExits("south", oficinas);
        control.setExits("west", almacen);
        armeria.setExits("west", oficinas);
        armeria.setExits("sureste",torre);
        torre.setExits("noroeste",armeria);

        // Ponemos objetos en las salas

        armeria.addItem(new Item("Pistola", 1.5f));
        control.addItem(new Item("Codigos", 0.05f));
        barracones.addItem(new Item("silenciador",0.5f));

        currentRoom = entrada;  // start game outside
         habitacionAnterior = null;
         contadorDeBack = 0;

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido a Infiltrandose en la base!");
        System.out.println("Infiltrandose en la base es un juego impresionante");
        System.out.println("Escribe help si necesitas ayuda");
        System.out.println();
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            Room lastRoom = currentRoom;
            goRoom(command);
            if(lastRoom != currentRoom){
                camino.push(lastRoom);
            }
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            System.out.println(currentRoom.getLongDescription());

        }
        else if(commandWord.equals("eat"))
        {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals("back")) {
            atras();            
            contadorDeBack = 0;
            
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Eres un espia, busca la sala de codigos");
        System.out.println();
        parser.imprimirComados();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
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
            habitacionAnterior = currentRoom;
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
            contadorDeBack++;
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * 
     * Metodo que muestra por pantalla la informacion de la localizacion
     */
    private void  printLocationInfo() 
    {
        System.out.println(currentRoom.getLongDescription());     
    }

    /**
     * Metodo que vuelve a la habitacion anterior en la que estuvo el jugador
     */
    private void atras()
    {              
        
        if(habitacionAnterior != null && contadorDeBack > 0)
         {
             Room nextRoom = habitacionAnterior;
             habitacionAnterior = currentRoom;
             currentRoom = nextRoom;
             printLocationInfo();
             System.out.println();
             
         }
         else
          {
              System.out.println("Lo siento, no pueder continuar");              
          }
         
      }
    }


