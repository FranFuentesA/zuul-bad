public class Item
 {
     // instance variables - replace the example below with your own
     private String descripcion;
     private float pesoKg;
 
     /**
      * Constructor for objects of class Item
      */
     public Item(String descripcion, float pesoKg)
     {
         this.descripcion = descripcion;
         this.pesoKg = pesoKg;
     }
 
     /**
      * Metodo que devulve el nombre/descripcion del objeto
      */
     public String getDescripcionObjeto(){
         return descripcion;
     }
 
     /**
      *  metodo que devuelve el peso en Kg del objeto
      */
     public float getPesoKg(){
         return pesoKg;
     }
 }