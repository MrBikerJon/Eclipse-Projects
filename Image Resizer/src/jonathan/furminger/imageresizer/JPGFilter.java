package jonathan.furminger.imageresizer;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class JPGFilter extends FileFilter {

	public boolean accept(File f) {
		boolean accept = false;
		String fileName = f.getName().toLowerCase();
		if(fileName.endsWith(".jpg") || f.isDirectory()) {
			accept = true;
		}
		return accept;
	}
	
	public String getDescription() {
		return "JPEG Images";
	}
	
}
