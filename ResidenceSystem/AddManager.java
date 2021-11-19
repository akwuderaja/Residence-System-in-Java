public class AddManager implements Command{
    @Override
    public void execute() {
        IOAccess.getInstance().outputString("-------Adding Manager to Residence-------");
        String mName = IOAccess.getInstance().readString("Enter the manager's name: ");
        String mSIN = IOAccess.getInstance().readString("Enter the manager's SIN: ");
        String mEN = IOAccess.getInstance().readString("Enter the manager's employee number: ");
        if (ManagerMapAccess.getInstance().containsKey(mEN))
            throw new IllegalStateException("Manager not added as there already "
                    + "is a manager with the employee number " + mEN);

        String response = IOAccess.getInstance().readString("Is the manager a consultant? (yes or no): ");

        Manager mgr;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            mgr = new Consultant(mName, mSIN, mEN);
        else
            mgr = new Manager(mName, mSIN, mEN);

        // check to make sure the manager name doesn't already exist
        Manager sameNumbermanager = ManagerMapAccess.getInstance().put(mEN, mgr);
        if (sameNumbermanager != null) {
            // if put() returns a reference, then a manager was already stored with the same EN,
            // so put it back, and signal an error.
            ManagerMapAccess.getInstance().put(mEN, sameNumbermanager); // put the original manager back
            throw new IllegalStateException("Employee number in the manager dictionary even though "
                    + "containsKey failed.  Manager " + mName + " not entered.");
        }
    }

    public static void main(String[] args) {
        Command cmd = new AddManager();
        cmd.execute();
    }
}
