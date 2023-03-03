package Organism.neurons.actions;

import Organism.Organism;

public class Talk extends ActionNeuron {

  private final String text;

  public Talk(float threshold, String text) {
    super(threshold);
    this.text = text;
  }

  @Override
  public void doAction(Organism organism) {
    if (!this.weightedSynapseSumGreaterThanThreshold()) return;
    System.out.print(organism);
    System.out.println(" says \"" + this.text + "\"");
  }
}
