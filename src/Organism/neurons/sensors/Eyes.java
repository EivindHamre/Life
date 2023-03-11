package Organism.neurons.sensors;

import Organism.Organism;
import environment.Grid;

public class Eyes implements ValueSender {

  private float value;
  private Organism organism;
  private Grid grid;

  public Eyes(Grid grid, Organism o) {
    this.grid = grid;
    this.organism = o;
  }

  @Override
  public float getValue() {
    return this.value;
  }

  @Override
  public void UpdateValue() {
    int x = organism.pos.x();
    int y = organism.pos.y();

    int dir = organism.direction;
    x += dir == 1 ? 1 : dir == 3 ? -1 : 0;
    y += dir == 0 ? 1 : dir == 2 ? -1 : 0;
    this.value = grid.isValidPos(x, y) ? 1 : 0;
  }
}
