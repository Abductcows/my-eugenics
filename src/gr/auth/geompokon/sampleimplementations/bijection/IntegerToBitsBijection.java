package gr.auth.geompokon.sampleimplementations.bijection;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.generification.TypeToIndividualBijection;

public class IntegerToBitsBijection implements TypeToIndividualBijection<Integer> {
    @Override
    public Individual toIndividual(Integer t) {
        return new Individual(
                String.format("%32s", Integer.toString(t, 2))
                        .replace(" ", "0")
        );
    }

    @Override
    public Integer toType(Individual individual) {
        return Integer.parseUnsignedInt(individual.getBinaryGenesString(), 2);
    }


}
