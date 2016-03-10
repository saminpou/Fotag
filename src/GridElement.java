import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class GridElement extends JPanel {

	public ImageModel imgModel;
	JLabel imageLabel;
	GridElementBottom gridElementBottom;
	
	Model m;
	public GridElement(Model model, ImageModel imgModel) {
		this.imgModel = imgModel;
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		model = m;
		this.setPreferredSize(new Dimension(185,200));
		this.setMaximumSize(new Dimension(185,200));
		setLayout(new BorderLayout(0, 0));
		
		imageLabel = new JLabel();
		Image image = imgModel.image.getImage();
		Image newimg = image.getScaledInstance(185, 100,  java.awt.Image.SCALE_SMOOTH);
		imageLabel.setIcon(new ImageIcon(newimg));
		imageLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(imageLabel, BorderLayout.CENTER);
		
		gridElementBottom = new GridElementBottom(m);
		add(gridElementBottom, BorderLayout.SOUTH);
		
		gridElementBottom.name.setText(imgModel.name);
		gridElementBottom.dateModified.setText(imgModel.date);
		
				
	}
	
	public ImageModel getImgModel() {
		return imgModel;
	}
	public void setImgModel(ImageModel imgModel) {
		
		this.imgModel = imgModel;
		imageLabel.setIcon(imgModel.image);
		
	}
	

}
