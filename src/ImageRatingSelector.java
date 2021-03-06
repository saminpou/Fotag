import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ImageRatingSelector extends JPanel implements Observer{

	public ArrayList<JButton> myStars;
	Model m;
	ImageModel imgModel;
	
	public ImageRatingSelector(Model model, ImageModel imgModel) {
		m = model;
		this.imgModel = imgModel;
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(250, 50));
		myStars = new ArrayList<JButton>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		int numStars = imgModel.rating;
		for (int i = 1; i < 6; i++) {
			final JButton b;
			if (i > numStars)
				b = new JButton("☆");
			else
				b = new JButton("★");
			
			myStars.add(b);
			b.setHorizontalAlignment(SwingConstants.LEFT);
			b.setVerticalAlignment(SwingConstants.TOP);
			b.setBorder(null);
			b.setPreferredSize(new Dimension(50, 50));
			b.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
			b.putClientProperty("column", i);
			b.setFocusable(false);
			b.setFocusTraversalKeysEnabled(false);
			b.setFocusPainted(false);
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton) e.getSource();
					clickStars((int) btn.getClientProperty("column"));
				}
			});
			b.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					startHover(b);
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					stopHover(b);
				}
			});

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = i;
			gbc.gridy = 0;
			add(b, gbc);

		}
	}

	public void clickStars(int btnIndex) {
		int pastIndex = imgModel.rating;
		if (btnIndex == pastIndex) {
			for (int x = 0; x < myStars.size(); x++) {
				myStars.get(x).setText("☆");
			}
			btnIndex = 0;
		} else {
			for (int x = 0; x < myStars.size(); x++) {
				myStars.get(x).setText("☆");
			}
			this.updateUI();
			for (int x = 0; x < btnIndex; x++) {
				myStars.get(x).setText("★");
			}
			this.updateUI();
		}
		m.changeRating(imgModel, btnIndex);
		//imgModel.rating = btnIndex;
		this.updateUI();
		
	}

	public void startHover(JButton b) {
		int buttonIndex = 0;
		for (int i = 0; i < myStars.size(); i++) {
			if (myStars.get(i).equals(b)) {
				buttonIndex = i;
			}
		}

		for (int x = 0; x < myStars.size(); x++) {
			myStars.get(x).setText("☆");
		}

		for (int x = 0; x <= buttonIndex; x++) {
			myStars.get(x).setText("★");
		}
		this.updateUI();
	}

	public void stopHover(JButton b){
		int i = m.filter;
		int pastIndex = imgModel.rating;
		if (pastIndex == 0) {
			for (int x = 0; x < myStars.size(); x++) {
				myStars.get(x).setText("☆");
			}
		} else {

			for (int x = 0; x < myStars.size(); x++) {
				myStars.get(x).setText("☆");
			}

			for (int x = 0; x < pastIndex; x++) {
				myStars.get(x).setText("★");
			}
		}
		this.updateUI();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.updateUI();	
	}
}

/*
 * if (m.filter == -1) { for (int x = m.filter; x < myStars.size(); x++) {
 * myStars.get(x).setText("☆"); } } else {
 * 
 * for (int x = m.filter; x < myStars.size(); x++) {
 * myStars.get(x).setText("☆"); }
 * 
 * for (int x = 0; x <= m.filter; x++) { myStars.get(x).setText("★"); } }
 */
