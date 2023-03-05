import Organism.Organism;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

  protected static final int NUMBER_OF_INTERNAL_NEURONS = 3;
  protected static final int ORGANISMS_PER_GENERATION = 10_000;
  protected static final int INITIAL_NUMBER_OF_GENES = 5;
  protected static final int STEPS_PER_GENERATION = 100;

  private static boolean survives(Organism o) {
    return o.pos.x() > 100;
  }

  public static void main(String[] args) {
    Random r = new Random();
    List<Organism> organisms = new ArrayList<>() {
      {
        for (int i = 0; i < ORGANISMS_PER_GENERATION; i++) add(
          Organism.randomOrganism(INITIAL_NUMBER_OF_GENES, NUMBER_OF_INTERNAL_NEURONS)
        );
      }
    };

    int gen = 0;
    while (true) {
      gen++;
      for (int i = 0; i < STEPS_PER_GENERATION; i++) {
        for (Organism o : organisms) {
          o.step();
        }
      }
      List<Organism> survivors = new ArrayList<>();
      for (Organism o : organisms) {
        if (survives(o)) {
          survivors.add(o);
        }
      }
      System.out.print(gen);
      System.out.print(": ");
      System.out.println(
        (float) (survivors.size()) / (float) (organisms.size())
      );
      if (survivors.size() < 1) {
        System.out.println("All organisms died");
        break;
      }
      organisms = new ArrayList<>();
      for (int i = 0; i < ORGANISMS_PER_GENERATION; i++) {
        organisms.add(survivors.get(r.nextInt(survivors.size())).replicate());
      }
    }
  }
}
