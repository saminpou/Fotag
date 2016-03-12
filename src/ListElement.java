import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class ListElement extends JPanel {

	public ImageModel imgModel;
	JLabel imageLabel;
	public JLabel name;
	public JLabel dateModified;
	
	Model m;
	public ListElement(Model model, ImageModel imgModel) {
		this.imgModel = imgModel;
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		m = model;
		this.setPreferredSize(new Dimension(400,100));
		this.setMaximumSize(new Dimension(400,100));
		setLayout(new BorderLayout(0, 0));
		
		imageLabel = new JLabel();
		imageLabel.setIcon(imgModel.scaledImage);
		imageLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(imageLabel, BorderLayout.CENTER);
		
		JPanel gridElementBottom1 = new JPanel();
		add(gridElementBottom1, BorderLayout.EAST);
		
		gridElementBottom1.setPreferredSize(new Dimension(200, 85));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridElementBottom1.setLayout(gridBagLayout);
		
		name = new JLabel(imgModel.name);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		gridElementBottom1.add(name, gbc_lblNewLabel);
		
		dateModified = new JLabel(imgModel.date);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		gridElementBottom1.add(dateModified, gbc_lblNewLabel_2);
		
		ImageRatingSelector ratingSelector = new ImageRatingSelector(m, imgModel);
		GridBagConstraints gbc_ratingSelector = new GridBagConstraints();
		gbc_ratingSelector.fill = GridBagConstraints.VERTICAL;
		gbc_ratingSelector.gridx = 0;
		gbc_ratingSelector.gridy = 2;
		gridElementBottom1.add(ratingSelector, gbc_ratingSelector);				
	}
	
	public ImageModel getImgModel() {
		return imgModel;
	}
	public void setImgModel(ImageModel imgModel) {
		
		this.imgModel = imgModel;
		imageLabel.setIcon(imgModel.image);
		
	}
	

}
