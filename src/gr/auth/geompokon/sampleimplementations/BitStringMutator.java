package gr.auth.geompokon.sampleimplementations;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.MutationMechanism;

import java.util.BitSet;
import java.util.Random;

public class BitStringMutator implements MutationMechanism {

    private double p;
    private final Random random = new Random();

    public BitStringMutator(double p) {
        this.p = p;
    }

    @Override
    public Individual mutate(Individual individual) {

        if (random.nextDouble() < p) {
            BitSet genes = individual.getGenes();
            double pFlip = 1d / genes.size();

            for (int i=0; i<genes.size(); i++) {
                if (random.nextDouble() < pFlip) {
                    genes.flip(i);
                }
            }
        }

        return individual;
    }

    public void setP(double newP) {
        this.p = newP;
    }
}
