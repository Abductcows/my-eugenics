package gr.auth.geompokon.interfaces;

import gr.auth.geompokon.Individual;

public interface CrossoverMechanism {

    Individual[] crossOver(Individual o1, Individual o2);
}
