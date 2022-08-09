import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// SRP 单一职责
class SingleResponsibilityPrinciple {
    private final List<String> entity = new ArrayList<>();
    private static int count=0;

    public void addEntry(String text){
        entity.add("" + (++count) + ":" + text);
    }

    public void removeEntry(int index){
        entity.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entity);
    }

    public void save(String filename){
        try {
            PrintStream out = new PrintStream(filename);
            out.println(toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class Persistence {
    public void saveToFile(SingleResponsibilityPrinciple srp,
                           String filename,
                           boolean overwrite){
        if (overwrite || new File(filename).exists()){
            try {
                PrintStream out = new PrintStream(filename);
                out.println(srp);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class SingleResponsibilityPrincipleDemo{
    public static void main(String[] args) throws IOException {
        SingleResponsibilityPrinciple srp = new SingleResponsibilityPrinciple();
        srp.addEntry("i am a boy");
        srp.addEntry("i am a girl");
        System.out.println(srp);

        Persistence p = new Persistence();
        String filename = "c:\\temp\\text.txt";
        p.saveToFile(srp, filename, true);

        Runtime.getRuntime().exec("notepad.exe " + filename);
    }
}
