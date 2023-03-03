package Organism.neurons.actions;

import Organism.Organism;
import environment.GridPosition;

public class Move extends ActionNeuron {

  public Move(float threshold) {
    super(threshold);
  }

  @Override
  public void doAction(Organism organism) {
    if (!weightedSynapseSumGreaterThanThreshold()) return;
    int x = organism.pos.x();
    int y = organism.pos.y();
    switch (organism.direction) {
      case 0:
        organism.pos = new GridPosition(x + 1, y);
        break;
      case 1:
        organism.pos = new GridPosition(x, y + 1);
        break;
      case 2:
        organism.pos = new GridPosition(x - 1, y);
        break;
      case 3:
        organism.pos = new GridPosition(x, y - 1);
        break;
    }
  }
}
