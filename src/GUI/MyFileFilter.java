/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Win 10
 */
public class MyFileFilter extends FileFilter{
    String description;
    String extension;
    
    public MyFileFilter()
    {
        description="";
        extension="";
    }
    
    public MyFileFilter(String description, String extension)
    {
        this.description=description;
        this.extension=extension;
    }
    @Override
    public boolean accept(File f) {
        if(f.getName().matches("\\w+.txt"))
            return true;
        return false;
    }
    public void setDescription(String description)
    {
        if(!description.isEmpty())
        this.description=description;
    }
    
    @Override
    public String getDescription() {
        return description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        if(extension.equals(".txt"))
        this.extension = extension;
    }
    
    
    
}
