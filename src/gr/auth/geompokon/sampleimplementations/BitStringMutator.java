package gr.auth.geompokon.sampleimplementations;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.MutationMechanism;

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
            byte[] genes = individual.getGenes();
            double pFlip = 1d / genes.length;

            for (int i=0; i<genes.length; i++) {
                if (random.nextDouble() < pFlip) {
                    genes[i] = (byte) (genes[i] == 1 ? 0 : 1);
                }
            }
        }

        return individual;
    }

    public void setP(double newP) {
        this.p = newP;
    }
}
