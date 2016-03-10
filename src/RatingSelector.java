import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JButton;

public class RatingSelector extends JPanel {

	public ArrayList<JButton> myStars;
	Model m;
	public RatingSelector(Model model) {
		m = model;
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(250,50));
		myStars = new ArrayList<JButton>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		for (int i = 0; i < 5; i++) {
			JButton b = new JButton("☆");
			myStars.add(b);
			b.setBorder(null);
			b.setPreferredSize(new Dimension(50, 50));
			b.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
			b.putClientProperty("column", i);
			b.setFocusable(false);
			b.setFocusTraversalKeysEnabled(false);
			b.setFocusPainted(false);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();
					modifyStars((int)btn.getClientProperty("column"));
				}
			});
				
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = i;
			gbc.gridy = 0;
			add(b, gbc);

		}
	}
	
	public void modifyStars(int btnIndex){	
		for (int x = 0; x < myStars.size(); x++){
			myStars.get(x).setText("☆");
		}
		for (int x = 0; x <= btnIndex; x++){
			myStars.get(x).setText("★");
		}
	}
}



