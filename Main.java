<html><body><strong>Sneaky!</strong></body></html>import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        String relative = "src/Main.java";
        String relativeTraversal = "src/../.gitignore";

        String absolute = "/var/log/";

        /*
        I've noticed odd behavior - whether it's going to be:
        * "/var/log/../../etc/passwd"; or
        "/var/log/../../../../../../../../../../etc/passwd";
        macos will still resolve it to /private/etc/passwd - is it macos specific behavior?
         */
        String absoluteTraversal = "/var/log/../../../../../../../etc/passwd";


        // OK, no FS access
        System.out.println("--- Path.normalize() ---");
        System.out.println(Paths.get(relative).normalize());
        System.out.println(Paths.get(relativeTraversal).normalize());
        System.out.println(Paths.get(absolute).normalize());
        System.out.println(Paths.get(absoluteTraversal).normalize());

        // NOT OK
        System.out.println("--- Path.toAbsolutePath() ---");
        System.out.println(Paths.get(relative).toAbsolutePath());
        System.out.println(Paths.get(relativeTraversal).toAbsolutePath());
        System.out.println(Paths.get(absolute).toAbsolutePath());
        System.out.println(Paths.get(absoluteTraversal).toAbsolutePath());

        // NOT OK
        System.out.println("--- Path.getParent() ---");
        System.out.println(Paths.get(relative).getParent());
        System.out.println(Paths.get(relativeTraversal).getParent());
        System.out.println(Paths.get(absolute).getParent());
        System.out.println(Paths.get(absoluteTraversal).getParent());

        // OK, FS access
        System.out.println("--- Path.getCanonicalPath() ---");
        System.out.println(Paths.get(relative).toFile().getCanonicalPath());
        System.out.println(Paths.get(relativeTraversal).toFile().getCanonicalPath());
        System.out.println(Paths.get(absolute).toFile().getCanonicalPath());
        System.out.println(Paths.get(absoluteTraversal).toFile().getCanonicalPath());

        // OK, no FS access
        System.out.println("--- Path.toRealPath() ---");
        System.out.println(Paths.get(relative).toRealPath());
        System.out.println(Paths.get(relativeTraversal).toRealPath());
        System.out.println(Paths.get(absolute).toRealPath());
        System.out.println(Paths.get(absoluteTraversal).toRealPath());
    }
}
