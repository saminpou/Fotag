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
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;

public class Model extends Observable {

	public ArrayList<ImageModel>[] ImageCollectionModel;
	public int filter = 0;
	public float xSize;
	public float ySize;
	public int columns = 4;
	public boolean isGrid = true;
	

	public Model() {
		ImageCollectionModel = (ArrayList<ImageModel>[]) new ArrayList[6];
		for (int x = 0; x < ImageCollectionModel.length; x++) {
			ImageCollectionModel[x] = new ArrayList<ImageModel>();
		}
		notifyObservers("Init");
		if (loadFile()) {
			loadImages();
		}
	}

	public boolean loadFile() {
		FileInputStream f_in = null;
		try {
			f_in = new FileInputStream("imageList.data");
			ObjectInputStream obj_in = new ObjectInputStream(f_in);
			Object obj = obj_in.readObject();
			if (obj instanceof ArrayList[]) {
				ImageCollectionModel = (ArrayList[]) obj;
			}
			return true;
		} catch (FileNotFoundException e) {

			System.out.println("imageList.data not found. A new imagelist will be created when the program exits");
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
			FileOutputStream f_out = new FileOutputStream("imageList.data");
			obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(ImageCollectionModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected ImageIcon createImageIcon(String path, String description) {
		if (path != null) {
			ImageIcon imgIcon = new ImageIcon(path, description);
			if (imgIcon.getIconWidth() == -1)
				return null;
			return imgIcon;
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void loadImages() {
		for (int i = 0; i < 6; i++) {
			for (int x = 0; x < ImageCollectionModel[i].size(); x++) {
				ImageModel imgModel = ImageCollectionModel[i].get(x);
				ImageIcon icon = createImageIcon(imgModel.path, "a pretty but meaningless splat");
				if (icon != null) {
					imgModel.image = icon;
					imgModel.scaledImage = new ImageIcon(imgModel.image.getImage().getScaledInstance(185, 100,
							java.awt.Image.SCALE_SMOOTH));
				} else {
					ImageCollectionModel[i].remove(x);
				}
			}
		}
	}

	public void loadImage(String path) {

		ImageIcon image = createImageIcon(path, "test");

		if (image != null) {
			Path p = Paths.get(path);
			BasicFileAttributes view = null;
			try {
				view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			java.nio.file.attribute.FileTime fileTimeCreation = view.creationTime();
			int idx = path.lastIndexOf("/");
			String name = idx >= 0 ? path.substring(idx + 1) : path;
			ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(185, 100,
					java.awt.Image.SCALE_SMOOTH));
			ImageModel i = new ImageModel(name, path, 0, fileTimeCreation.toString(), image,scaledImage);
			ImageCollectionModel[0].add(i);
			if (filter == 0) {
				this.setChanged();
				this.notifyObservers();
			}
		}

	}

	public void updateAllDisplayedImages() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public void changeRating(ImageModel imgModel, int btnIndex){	
		imgModel.rating = btnIndex;
		fixRatings();
		this.setChanged();
		this.notifyObservers();
	}
	public void fixRatings(){
		for (int x = 0; x < ImageCollectionModel.length; x++){
			for (int y = 0; y < ImageCollectionModel[x].size(); y++){
				if (ImageCollectionModel[x].get(y).rating != x){
					ImageModel imgModel = ImageCollectionModel[x].get(y);
					ImageCollectionModel[x].remove(y);
					ImageCollectionModel[imgModel.rating].add(imgModel);
				}
			}
		}
	}
	public void calculateNumColumns(int width){
		if (width/185 != columns){
			if (columns != 0)
				columns = width/185;
			this.setChanged();
			this.notifyObservers("");
			System.out.println(columns);
			System.out.println(width);
		}
	}
	
	public void changeCardType(){
		isGrid = !isGrid;
		this.setChanged();
		this.notifyObservers("ViewChange");
	}
	
}
