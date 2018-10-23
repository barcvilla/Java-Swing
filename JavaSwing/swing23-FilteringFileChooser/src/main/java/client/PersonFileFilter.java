/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author PC
 */
public class PersonFileFilter extends FileFilter{

    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory())
        {
            return true;
        }
        
        String name = pathname.getName();
        String extension = Utils.getFileExtension(name);
        if(extension == null)
        {
            return false;
        }
        
        if(extension == "per")
        {
            return true;
        }
        
        return false;
    }

    @Override
    public String getDescription() {
        return "Person database files (*.per)";
    }
}
