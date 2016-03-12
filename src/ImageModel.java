import java.io.Serializable;

import javax.swing.ImageIcon;

public class ImageModel implements Serializable{
	
	String name;
	String path;
	int rating;
	String date;
	public transient ImageIcon image;
	public transient ImageIcon scaledImage;
	
	public ImageModel(String name, String path, int rating, String date){
		this.name = name;
		this.path = path;
		this.rating = rating;
		this.date = date;
	}
	
	public ImageModel(String name, String path, int rating, String date, ImageIcon image, ImageIcon scaledImage){
		this.name = name;
		this.path = path;
		this.rating = rating;
		this.date = date;
		this.image = image;
		this.scaledImage = scaledImage;
	}
	
}
