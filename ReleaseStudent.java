public class ReleaseStudent implements Command{
    @Override
    public void execute() {
        // TODO: implement stub
        String NSID =  IOAccess.getInstance().readString("Enter Student's NSID:");
        if (NSID == ""){
            IOAccess.getInstance().outputString("The NSID is required!");
        }else{
            Student student = StudentMapAccess.getInstance().get(NSID);
            if(student==null){
                IOAccess.getInstance().outputString("The NSID is not valid!");
            }else{
                ResidenceAccess.getInstance().freeBed(student.getBedLabel());
                IOAccess.getInstance().outputString("The selected student has been removed from his/her bed!");
            }
        }

    }


    public static void main(String[] args) {
        Command cmd = new ReleaseStudent();
        cmd.execute();
    }
}
