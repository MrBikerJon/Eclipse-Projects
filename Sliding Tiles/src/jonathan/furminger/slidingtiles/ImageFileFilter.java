package jonathan.furminger.slidingtiles;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter {

	public String getDescription() {
		return "All image files: *.jpg, *.png, *.gif";
	}
	
	public boolean accept(File file) {
		boolean accept = false;
		String fileName = file.getName().toLowerCase();
		if(fileName.endsWith(".jpg") 
				|| fileName.endsWith(".png") 
				|| fileName.endsWith(".gif")
				|| file.isDirectory()) {
			accept = true;
		}
		return accept;
	}
}
