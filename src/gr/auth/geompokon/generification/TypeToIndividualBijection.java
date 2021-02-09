package gr.auth.geompokon.generification;

import gr.auth.geompokon.Individual;

public interface TypeToIndividualBijection<T> {

    Individual toIndividual(T t);

    T toType(Individual individual);
}
