
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("ir"),QUIT("quitar"), HELP("ayuda"), LOOK("buscar"),EAT("comer"),BACK("atras"), TAKE("coger"), ITEMS("bolsa"), DROP("tirar"), UNKNOWN(" "),POINTS("Puntos");
    
    private String comando;
    private Option(String comando) {
        this.comando = comando;
    }

    /**
     * Metodo que devuelve el comando
     */
    public String getComando(){
        return comando;
    }
}