package gr.auth.geompokon.implementations.mutation;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.MutationMechanism;

import java.util.Random;

public class BitStringMutation implements MutationMechanism {

    private double p;
    private final Random random = new Random();

    public BitStringMutation(double p) {
        this.p = p;
    }

    @Override
    public void mutate(Individual individual) {

        if (random.nextDouble() < p) {
            byte[] genes = individual.getGenes();
            double pFlip = 1d / genes.length;

            for (int i=0; i<genes.length; i++) {
                if (random.nextDouble() < pFlip) {
                    genes[i] = (byte) (genes[i] == 1 ? 0 : 1);
                }
            }
        }

    }

    public void setP(double newP) {
        this.p = newP;
    }
}
