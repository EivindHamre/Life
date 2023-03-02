import Organism.Organism;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
  public static void main(String[] args) throws Exception {
    Random r = new Random();
    List<Organism> organisms = new ArrayList<>() {
      {
        for (int i = 0; i < 1000; i++) add(Organism.randomOrganism(5, 1));
      }
    };

    int gen = 0;
    while (true) {
      gen++;
      for (int i = 0; i < 1000; i++) {
        for (Organism o : organisms) {
          o.step();
        }
      }
      List<Organism> survivors = new ArrayList<>();
      for (Organism o : organisms) {
        if (o.pos.x() > 64) {
          survivors.add(o);
        }
      }
      System.out.print(gen);
      System.out.print(": ");
      System.out.println((float)(survivors.size()) / (float)(organisms.size()));
      organisms = new ArrayList<>();
      for (int i = 0; i < 100; i++) {
        organisms.add(survivors.get(r.nextInt(survivors.size())).replicate());
      }
    }
  }
}
