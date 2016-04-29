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
    private Player jugador;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        jugador = new Player(30);
        createRooms();
        parser = new Parser();      

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

        armeria.addItem(new Item("Pistola", 3.5f,true));
        armeria.addItem(new Item("MisilATT",40f,true));
        control.addItem(new Item("Codigos", 0.05f,true));
        barracones.addItem(new Item("silenciador",0.5f,true));
        barracones.addItem(new Item("Cama",20f,false));
        oficinas.addItem(new Item("Silla de oficina",9f,false));
        torre.addItem(new Item("foco",27f,true));

        jugador.fijarHabitacion(entrada);  // start game outside
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
        System.out.println("Escribe aiuto si necesitas ayuda");
        System.out.println();
        jugador.printLocationInfo();
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

        Option commandWord = command.getCommandWord();

        switch(commandWord){
            case HELP:
            printHelp();
            break;
            case GO:
                jugador.goRoom(command);
            break;
            case QUIT:
                wantToQuit = quit(command);
            break;
            case LOOK:
                jugador.printLocationInfo();
            break;            
            case TAKE:
                jugador.cogeObjeto(command.getSecondWord());
            break;   
            case DROP:
                jugador.tiraObjeto(command.getSecondWord());
            break;    
            case ITEMS:
                jugador.inventario();
            break;
            case EAT:            
                System.out.println("You have eaten now and you are not hungry any more");
            break;
            case BACK:
                jugador.atras();
                jugador.printLocationInfo();
            break;
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

}

