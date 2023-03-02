package Organism.neurons.actions;

import Organism.Organism;
import environment.GridPosition;

public class Move implements ActionNeuron {

  final float threshold;
  public final SynapseCollection synapses;

  public Move(float threshold) {
    this.threshold = threshold;
    this.synapses = new SynapseCollection();
  }

  @Override
  public void doAction(Organism organism) {
    if (this.synapses.getValue() < this.threshold) return;
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
