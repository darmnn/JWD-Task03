package by.tc.task03.validation.impl;

import by.tc.task03.validation.FileValidator;

import java.io.File;

public class XMLValidator implements FileValidator
{
    private File fileToValidate;

    public XMLValidator(String path)
    {
        fileToValidate = new File(path);
    }

    public boolean validate()
    {
        return false;
    }
}
