package by.tc.task03.parsing;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Reader {
    public String readFullFile() throws FileNotFoundException, IOException;
}
