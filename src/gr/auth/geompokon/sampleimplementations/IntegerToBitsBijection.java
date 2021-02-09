package gr.auth.geompokon.sampleimplementations;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.generification.TypeToIndividualBijection;

public class IntegerToBitsBijection implements TypeToIndividualBijection<Integer> {
    @Override
    public Individual toIndividual(Integer t) {
        return new Individual(Integer.toBinaryString(t));
    }

    @Override
    public Integer toType(Individual individual) {
        return Integer.parseInt(individual.getBinaryGenesString(), 2);
    }
}
