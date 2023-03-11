package Organism.neurons.actions;

import java.util.Random;

import Organism.Organism;
import environment.Grid;

public class Jiggle extends Move {

  private Random r;

  public Jiggle(float threshold, Grid grid) {
    super(threshold, grid);
    this.r = new Random();
  }
  
  @Override
  public void doAction(Organism organism) {
    this.grid.move(organism.pos, r.nextInt(3));
  }
}
