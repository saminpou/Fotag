import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;

public class Model extends Observable {

	public ArrayList<ImageModel> ImageCollectionModel;
	public ArrayList<ImageModel> DisplayedImageCollectionModel;
	public int filter = 0;
	public float xSize;
	public float ySize;
	public int columns = 4;
	public boolean listChanged;
	
	
	public Model() {
		ImageCollectionModel = new ArrayList<ImageModel>();
		DisplayedImageCollectionModel = new ArrayList<ImageModel>();
		notifyObservers("Init");
		if (loadFile()) {
			loadImages();
		}
	}

	public boolean loadFile() {
		FileInputStream f_in = null;
		try {
			f_in = new FileInputStream("/imageList.data");
			ObjectInputStream obj_in = new ObjectInputStream(f_in);
			Object obj = obj_in.readObject();
			if (obj instanceof ArrayList) {
				ImageCollectionModel = (ArrayList) obj;
				notifyAll();
			}
			return true;
		} catch (FileNotFoundException e) {

			System.out.println("imageList.data not found. A new imagelist will be created when the program exits");
			//e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void saveFile() {
		ObjectOutputStream obj_out = null;
		try {
			FileOutputStream f_out = new FileOutputStream("/imageList.data");
			obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(ImageCollectionModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected ImageIcon createImageIcon(String path, String description) {
		//java.net.URL imgURL = getClass().getResource(path);
		if (path != null) {
			return new ImageIcon(path, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public void loadImages(){
		for (int x = 0; x < ImageCollectionModel.size(); x++) {
			ImageModel m = ImageCollectionModel.get(x);
			ImageIcon icon = createImageIcon(m.path, "a pretty but meaningless splat");
			if (icon != null){
				m.image = icon;
			}else{
				ImageCollectionModel.remove(x);
			}
		}
	}
	
	public void loadImage(String path){

		ImageIcon image = createImageIcon(path, "test");
		
		if (image != null){
			Path p = Paths.get(path);
			BasicFileAttributes view = null;
			try {
			    view = Files.getFileAttributeView( p, BasicFileAttributeView.class ).readAttributes();
			} catch ( IOException e ) {
			    e.printStackTrace();
			}
			java.nio.file.attribute.FileTime fileTimeCreation = view.creationTime();
			int idx = path.lastIndexOf("/");
			String name =  idx >= 0 ? path.substring(idx + 1) : path;
			ImageModel i = new ImageModel(name, path, 0, fileTimeCreation.toString(), image);
			ImageCollectionModel.add(i);
			if (filter == 0){
				DisplayedImageCollectionModel.add(i);
				System.out.println(this.countObservers());
				this.setChanged();
				this.notifyObservers("newImage");
			}
		}
		
	}
}
