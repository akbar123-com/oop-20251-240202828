import java.util.function.BiConsumer;
 public class HelloFunctional
  { public static void main(String[] args) 
    { BiConsumer<String,Integer> sapa = (nama, nim) -> 
      System.out.println("Halo world, i am, " + nama + " - " + nim);
     sapa.accept("AKHMAD AKBAR SYARIFUDIN", 240202828); 
    } 
} 