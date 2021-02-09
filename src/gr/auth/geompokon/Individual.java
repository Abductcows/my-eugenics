package gr.auth.geompokon;

public class Individual {

    byte[] genes;

    public Individual(byte[] genes) {
        this.genes = genes;
    }

    public Individual(String binaryGenes) {
        genes = new byte[binaryGenes.length()];

        for (int i=0; i<binaryGenes.length(); i++) {
            if (binaryGenes.charAt(i) == '1') {
                genes[i] = 1;
            }
        }
    }

    public byte[] getGenes() {
        return genes;
    }

    public String getBinaryGenesString() {
        return calculateBinaryGenes();
    }

    protected String calculateBinaryGenes() {
        StringBuilder result = new StringBuilder(genes.length);

        for (byte gene : genes) {
            result.append(gene == 1 ? 1 : 0);
        }

        return result.toString();
    }
}
