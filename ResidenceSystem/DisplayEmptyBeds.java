public class DisplayEmptyBeds implements Command{
    @Override
    public void execute() {
        // TODO: implement stub
        String beds = "Available beds\n\t";
        for (int i = 0; i < ResidenceAccess.getInstance().availableBeds().size(); i++){
            beds = beds + String.valueOf(ResidenceAccess.getInstance().availableBeds().get(i)) + "\n\t";
        }
        IOAccess.getInstance().outputString(beds);
    }


    public static void main(String[] args) {
        Command cmd = new DisplayEmptyBeds();
        cmd.execute();
    }
}
