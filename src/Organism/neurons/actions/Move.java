package Organism.neurons.actions;

import Organism.Organism;
import environment.Grid;

public class Move extends ActionNeuron {

  protected final Grid grid;

  public Move(float threshold, Grid grid){
    super(threshold);
    this.grid = grid;
  }

  @Override
  public void doAction(Organism organism) {
    if (!weightedSynapseSumGreaterThanThreshold()) return;
    organism.pos = this.grid.move(organism.pos, organism.direction);
  }
}
