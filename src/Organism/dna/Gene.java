package Organism.dna;

import java.util.Random;

public record Gene(int code) {

  public int getInputNeuronID(){
    return code >>> 25;
  }
  
  public boolean inputIsInternalNeuron(){
    return (code & 1<<24) == 1<<24;
  }
  
  public int getOutputId(){
    return 0x7f & code >>> 17;
  }

  public boolean outputIsInternalNeuron(){
    return (code & 1<<16) == 1<<16;
  }

  public float getWeight(){
    return Float.intBitsToFloat(0xffff0000 & code << 16) / 4.2369142e+37f;
  }

  public Gene replicate(){
    Random r = new Random();
    if(r.nextInt(10) == 0) return new Gene(code ^ (1<<r.nextInt(32)));
    return new Gene(code);
  }
}
