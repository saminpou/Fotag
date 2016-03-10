import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;

public class ImageView extends JPanel {

	Model m;
	ImageModel imgModel;
	int index;
	
	public ImageView(int x, ImageModel imgModel, Model model) {
		m = model;
		index = x;
		this.imgModel = imgModel;
		setLayout(new CardLayout(0, 0));
		
		GridElement gridElement = new GridElement(m, imgModel);
		add(gridElement, "name_20939517081845");
		
		ListElement listElement = new ListElement();
		add(listElement, "name_20944138302348");
	}

}
