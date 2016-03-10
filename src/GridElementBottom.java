import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridElementBottom extends JPanel {
	public JLabel name;
	public JLabel dateModified;
	Model m;
	public GridElementBottom(Model model) {
		m = model;
		this.setPreferredSize(new Dimension(200, 85));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		name = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(name, gbc_lblNewLabel);
		
		dateModified = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		add(dateModified, gbc_lblNewLabel_2);
		
		RatingSelector ratingSelector = new RatingSelector(m);
		GridBagConstraints gbc_ratingSelector = new GridBagConstraints();
		gbc_ratingSelector.fill = GridBagConstraints.VERTICAL;
		gbc_ratingSelector.gridx = 0;
		gbc_ratingSelector.gridy = 2;
		add(ratingSelector, gbc_ratingSelector);

	}

}
