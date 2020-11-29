package genetic;

public class GeneticConfig {
	
	public static final int N_ATRRIBUTES 		= 3;
	public static final int N_CLASSES			= 5;
	
	public static final int [] NEURAL_RANGE 	= {N_ATRRIBUTES, N_ATRRIBUTES + N_CLASSES};
	public static final int DELTA_NEURAL 		= 1;
	public static final int NEURAL_BITS 		= 4;
	
	public static final int [] HIDDEN_RANGE 	= {1, 5};
	public static final int DELTA_HIDDEN 		= 1;
	public static final int HIDDEN_BITS 		= 3;
	
	public static final int [] EPOCH_RANGE 		= {100, 1000};
	public static final int DELTA_EPOCH 		= 20;
	public static final int EPOCH_BITS 			= 3;
	
	public static final float [] LEARNING_RANGE	= {0.001f, 0.3f};
	public static final int DELTA_LEARNING 		= 3;
	public static final int DELTA_BITS 			= 3;
	
	public static final float [] MOMENTUM_RANGE	= {0.001f, 0.3f};
	public static final int DELTA_MOMENTUM 		= 3;
	public static final int MOMENTUM_BITS 		= 3;
	
	public static final int CHROMOSOME_SIZE 	= NEURAL_BITS + HIDDEN_BITS + EPOCH_BITS + DELTA_BITS + MOMENTUM_BITS;
	
	public static final int INITIAL_POPULATION 	= 100;
	public static final int SURVIVAL_FACTOR 	= 100;
	public static final int CHILDS 				= 2;
	public static final float LIMIT				= 80f;
	
}
