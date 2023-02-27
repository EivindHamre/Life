import Organism.Organism;
import Organism.dna.DNA;
import Organism.dna.Gene;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class App {

  public static void main(String[] args) throws Exception {
    Organism o = new Organism(
      new DNA(
        new ArrayList<>() {
          {
            add(new Gene(0b00000000_00000001_0111111101111111));
            add(new Gene(0b00000001_00000000_0111111101111111));
          }
        },
        (short) 3
      )
    );
    while (true) {
      o.step();
      TimeUnit.MILLISECONDS.sleep(1000);
    }
  }
}
