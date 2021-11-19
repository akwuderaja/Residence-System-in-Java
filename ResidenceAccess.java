public class ResidenceAccess {

    /**
     * The residence to be handled.
     */
    private static Residence residence;

    public static void initialize(String name, int first, int last){
        if(residence==null) residence = new Residence(name,first,last);
        else throw new IllegalStateException("Residence already initialized");
    }
    public static Residence getInstance(){
        if(residence==null) throw new IllegalStateException("Residence not yet initialized");
        return residence;
    }
}
