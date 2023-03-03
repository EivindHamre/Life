package Organism.neurons.actions;

import Organism.Organism;

public class Turn extends ActionNeuron {

  private final int direction;

  public Turn(float threshold, int direction) {
    super(threshold);
    this.direction = direction;
  }

  @Override
  public void doAction(Organism organism) {
    if (!this.weightedSynapseSumGreaterThanThreshold()) return;
    organism.direction += this.direction;
    organism.direction %= 4;
  }
}
