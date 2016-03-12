import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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

public class GridDisplay extends JPanel implements Observer {
	float columns;
	Model m;
	ArrayList<ImageView> images;
	GridBagLayout gridBagLayout;

	public GridDisplay(Model model) {

		m = model;
		m.addObserver(this);
		images = new ArrayList<ImageView>();
		populateImages();

		gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 1, 1 };
		gridBagLayout.columnWidths = new int[] { 1, 1, 1, 1 };
		gridBagLayout.columnWeights = new double[] {};
		gridBagLayout.rowWeights = new double[] {};

		setLayout(gridBagLayout);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		addImage();
	}

	public void addImage() {
		for (int i = 0; i < images.size(); i++) {
			int x;
			int y;
			if (m.isGrid) {
				x = i % m.columns;
				y = i / m.columns;
			} else {
				x = 0;
				y = i;
			}
			ImageView imgView = images.get(i);
			imgView.setAlignmentY(Component.TOP_ALIGNMENT);
			imgView.setAlignmentX(Component.LEFT_ALIGNMENT);
			GridBagConstraints gbc_gridElement = new GridBagConstraints();
			// gbc_gridElement.fill = GridBagConstraints.NONE;
			gbc_gridElement.insets = new Insets(0, 0, 0, 0);
			gbc_gridElement.gridx = x;
			gbc_gridElement.gridy = y;
			add(imgView, gbc_gridElement);
		}
	}

	public void removeImage() {
		for (int i = 0; i < images.size(); i++) {
			this.removeAll();
		}
	}

	public void populateImages() {
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < m.ImageCollectionModel[y].size(); x++) {
				ImageView i = new ImageView(m.ImageCollectionModel[y].get(x), m);
				if (!m.isGrid) {
					i.convertGridElements();
				}
				images.add(i);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {

		removeAll();
		int currentFilter = m.filter;
		images.clear();
		if (m.filter == 0) {
			populateImages();
			addImage();
		} else {
			for (int x = currentFilter; x < 6; x++) {
				for (int i = 0; i < m.ImageCollectionModel[x].size(); i++) {
					images.add(new ImageView(m.ImageCollectionModel[x].get(i), m));
				}
				addImage();
			}
		}

		updateUI();
	}
}
