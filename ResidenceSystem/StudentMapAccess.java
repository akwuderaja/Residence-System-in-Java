import java.util.Map;
import java.util.TreeMap;

public class StudentMapAccess {

    /**
     * The keyed dictionary of all students.
     */
    private static Map<String, Student> students = new TreeMap<String, Student>();
    public static Map<String, Student> getInstance(){
        return students;
    }
}
