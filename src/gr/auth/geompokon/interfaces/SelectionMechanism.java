package gr.auth.geompokon.interfaces;

import gr.auth.geompokon.Population;

public interface SelectionMechanism {

    Population selectForCrossover(Population population, FitnessFunction fitnessFunction);
}
