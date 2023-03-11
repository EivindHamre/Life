package Organism;

import Organism.dna.DNA;
import Organism.dna.Gene;
import Organism.neurons.InternalNeuron;
import Organism.neurons.actions.ActionNeuron;
import Organism.neurons.actions.Jiggle;
import Organism.neurons.actions.Move;
// import Organism.neurons.actions.Talk;
import Organism.neurons.actions.Turn;
import Organism.neurons.sensors.Constant;
import Organism.neurons.sensors.Direction;
import Organism.neurons.sensors.Oscilllator;
import Organism.neurons.sensors.ValueSender;
import environment.Grid;
import environment.GridPosition;
import java.util.ArrayList;
import java.util.Random;

public class Organism {

  static Random r = new Random();
  private final Grid grid;
  public GridPosition pos;
  public int direction;

  final DNA dna;
  final InternalNeuron[] internalNeurons;
  final ValueSender[] sensors;
  public final ActionNeuron[] actions;

  private void createSynapse(Gene g) {
    boolean inputIsIneternalNeuron = g.inputIsInternalNeuron();
    boolean outputIsInternalNeuron = g.outputIsInternalNeuron();
    int inputID = g.getInputNeuronID();
    int outputID = g.getOutputId();
    float weight = g.getWeight();
    ValueSender input = inputIsIneternalNeuron
      ? internalNeurons[inputID % internalNeurons.length]
      : sensors[inputID % sensors.length];
    if (outputIsInternalNeuron) {
      internalNeurons[outputID % internalNeurons.length].newSynapse(
          input,
          weight
        );
    } else {
      actions[outputID % actions.length].setNewSynapse(input, weight);
    }
  }

  public Organism(DNA dna, Grid grid) {
    this.dna = dna;
    this.internalNeurons = dna.makeInternalNeurons();
    this.grid = grid;
    this.direction = Organism.r.nextInt(4);
    this.pos =
      new GridPosition(Organism.r.nextInt(this.grid.rows()), Organism.r.nextInt(this.grid.cols()));

    this.actions =
      new ActionNeuron[] {
        // new Talk(0.5f, "'ello"),
        new Move(0, this.grid),
        new Jiggle(0, this.grid),
        new Turn(0, 1),
        new Turn(0, 3),
      };

    this.sensors =
      new ValueSender[] {
        new Oscilllator(),
        new Constant(),
        new Direction(this),
      };

    for (Gene g : dna.synapseGenes()) {
      createSynapse(g);
    }
  }

  public static Organism randomOrganism(Grid grid, int genes, int internalNeurons) {
    return new Organism(
      new DNA(
        new ArrayList<>() {
          {
            for (int i = 0; i < genes; i++) {
              add(new Gene(r.nextInt()));
            }
          }
        },
        (short) internalNeurons
      ),
      grid
    );
  }

  public void step() {
    for (ValueSender s : this.sensors) s.UpdateValue();
    for (InternalNeuron n : this.internalNeurons) n.UpdateValue();
    for (ActionNeuron a : this.actions) a.doAction(this);
  }

  public Organism replicate() {
    return new Organism(this.dna.replicate(), this.grid);
  }
}
