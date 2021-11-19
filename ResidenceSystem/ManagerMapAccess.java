import java.util.TreeMap;

public class ManagerMapAccess {
    /**
     * The keyed dictionary of all Managers.
     */
    private static TreeMap<String, Manager> managers = new TreeMap<String, Manager>();
    public static TreeMap<String, Manager> getInstance(){
        return managers;
    }
}
