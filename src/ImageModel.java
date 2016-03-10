import java.nio.file.attribute.FileTime;

import javax.swing.ImageIcon;

public class ImageModel {
	
	String name;
	String path;
	int rating;
	String date;
	public ImageIcon image;
	
	public ImageModel(String name, String path, int rating, String date){
		this.name = name;
		this.path = path;
		this.rating = rating;
		this.date = date;
	}
	
	public ImageModel(String name, String path, int rating, String date, ImageIcon image){
		this.name = name;
		this.path = path;
		this.rating = rating;
		this.date = date;
		this.image = image;
	}
	
}
