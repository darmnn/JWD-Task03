package by.tc.task03.validation;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileValidator {
    public boolean validate() throws FileNotFoundException, IOException;
}
