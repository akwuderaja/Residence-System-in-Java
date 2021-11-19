import java.util.NoSuchElementException;

public class DropAssociation implements Command{
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("-------Dropping a new Manager-Student Association-------");
        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        Student st = StudentMapAccess.getInstance().get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        String mEN = IOAccess.getInstance().readString("Enter the employee number of the manager: ");

        // check if the manager is known
        Manager mgr = ManagerMapAccess.getInstance().get(mEN);
        if (mgr == null)
            throw new NoSuchElementException("There is no manager with employee number " + mEN);

        String sNSID2 = st.getNSID();
        if(!sNSID.equals(sNSID2))
            throw new IllegalStateException("NSIDs are not equal for a studnet: " + sNSID + " " + sNSID2);

        // check if the manager records the student
        if (!mgr.hasStudent(sNSID2))
            throw new IllegalStateException("This manager is not associated with this student:" + sNSID2);

        // check if the student records the manager
        if (!st.hasManager(mEN))
            throw new IllegalStateException("This manager and this student are incorrectly "
                    + "associated.  The manager has the student, "
                    + "but the student does not have the manager");

        // the student and manager are associated, so we remove each from the other's record
        st.removeManager(mEN);
        mgr.removeStudent(sNSID);
    }


    public static void main(String[] args) {
        Command cmd = new DropAssociation();
        cmd.execute();
    }
}
