package Organism.neurons.actions;

import Organism.Organism;
import Organism.neurons.sensors.ValueSender;

public interface ActionNeuron {
    public void doAction(Organism organism);
    public void setNewSynapse(ValueSender input, float weight);
}
