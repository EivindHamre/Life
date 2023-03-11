import Organism.Organism;
import environment.Grid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class App {

  protected static final int NUMBER_OF_INTERNAL_NEURONS = 2;
  protected static final int ORGANISMS_PER_GENERATION = 1000;
  protected static final int INITIAL_NUMBER_OF_GENES = 10;
  protected static final int STEPS_PER_GENERATION = 2000;
  protected static final int[] GRID_SIZE = { 1280, 1280 };

  private static boolean survivalCriteria(Organism o) {
    // return true;
    return o.pos.x() < 400;
  }

  public static void main(String[] args) {
    Grid grid = new Grid(GRID_SIZE[0], GRID_SIZE[1]);
    Random r = new Random();
    for (int i = 0; i < ORGANISMS_PER_GENERATION; i++) {
      grid.placeRandom(
        Organism.randomOrganism(
          grid,
          INITIAL_NUMBER_OF_GENES,
          NUMBER_OF_INTERNAL_NEURONS
        )
      );
    }

    int gen = 0;
    while (true) {
      gen++;

      for (int i = 0; i < STEPS_PER_GENERATION; i++) {
        for (Organism o : grid.getOrganisms()) {
          o.step();
        }
      }

      List<Organism> survivors = grid
        .getOrganisms()
        .stream()
        .filter(o -> survivalCriteria(o))
        .collect(Collectors.toList());
      if(survivors.size() > 0.9 * ORGANISMS_PER_GENERATION){
        int a = 0;
        int b = a++;
        a += b;
      }
      System.out.print(gen);
      System.out.print(": ");
      System.out.println(
        (float) survivors.size() / (float) ORGANISMS_PER_GENERATION
      );
      if (survivors.size() < 1) {
        System.out.println("All organisms died");
        break;
      }
      grid = new Grid(GRID_SIZE[0], GRID_SIZE[1]);
      for (int i = 0; i < ORGANISMS_PER_GENERATION; i++) {
        grid.placeRandom(
          (survivors.get(r.nextInt(survivors.size())).replicate())
        );
      }
    }
  }
}
