import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.EtchedBorder;

public class GridDisplay extends JPanel {
	float columns;
	Model m;
	ArrayList <ImageView> images;
	public GridDisplay(Model model, ArrayList <ImageView> images) {
		
		m = model;
		this.images = images;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] {200, 200};
		gridBagLayout.columnWidths = new int[] {185, 185, 185, 185};
		gridBagLayout.columnWeights = new double[]{};
		gridBagLayout.rowWeights = new double[]{};
		
		setLayout(gridBagLayout);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setAlignmentY(Component.TOP_ALIGNMENT);
		
		for (int i = 0; i < images.size(); i++){
			int x = i % m.columns;
			int y = i / m.columns;
			ImageView imgView = images.get(i);
			imgView.setAlignmentY(Component.TOP_ALIGNMENT);
			imgView.setAlignmentX(Component.LEFT_ALIGNMENT);
			GridBagConstraints gbc_gridElement = new GridBagConstraints();
			gbc_gridElement.fill = GridBagConstraints.NONE;
			gbc_gridElement.gridx = x;
			gbc_gridElement.gridy = y;
			add(imgView, gbc_gridElement);
		}
	}
	
	public void addImage(){
		int i = images.size() - 1;
		int x = i % m.columns;
		int y = i / m.columns;
		ImageView imgView = images.get(i);
		imgView.setAlignmentY(Component.TOP_ALIGNMENT);
		imgView.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_gridElement = new GridBagConstraints();
		gbc_gridElement.fill = GridBagConstraints.NONE;
		gbc_gridElement.insets = new Insets(0, 0, 0, 0);
		gbc_gridElement.gridx = x;
		gbc_gridElement.gridy = y;
		add(imgView, gbc_gridElement);
	}
	
}
