import java.util.Collection;

public class SystemState implements Command{
    @Override
    public void execute() {
        String result = "\nThe students in the system are:";
        Collection<Student> patientsColl = StudentMapAccess.getInstance().values();
        for (Student p : patientsColl)
            result = result + p;
        result = result + "\n-------\nThe managers in the system are:";
        Collection<Manager> managersColl = ManagerMapAccess.getInstance().values();
        for (Manager d : managersColl)
            result = result + d;
        result = result + "\n-------\nThe residence is " + ResidenceAccess.getInstance();
        IOAccess.getInstance().outputString(result);
    }


    public static void main(String[] args) {
        Command cmd = new SystemState();
        cmd.execute();
    }
}
