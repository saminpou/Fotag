import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ImageDisplay extends JFrame {

	private JPanel contentPane;
	private JLabel imageLabel;
	private JScrollPane scrollGrid;
	private JPanel imagePanel;
	
	public ImageDisplay(ImageIcon i) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setMaximumSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setResizable(false);
		
		i = new ImageIcon(i.getImage().getScaledInstance(800, 600,
				java.awt.Image.SCALE_SMOOTH));
		
		imagePanel = new JPanel();
		imageLabel = new JLabel();
		imageLabel.setIcon(i);
		imagePanel.add(imageLabel, BorderLayout.CENTER);
		
		scrollGrid = new JScrollPane(imagePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollGrid, BorderLayout.CENTER);
		
		this.pack();
		
	}

}
