import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame {

	private JPanel contentPane;
	private Map<String, ImageIcon> imageMap;
	Model m;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		m = new Model();

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Topbar topbar = new Topbar(m);
		contentPane.add(topbar, BorderLayout.NORTH);

		PanelContainer panelContainer = new PanelContainer(m);
		contentPane.add(panelContainer, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		/*
		 * JMenuItem mntmSave = new JMenuItem("Save"); mnFile.add(mntmSave);
		 * 
		 * mntmSave.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent evt) { //SaveFileEvent(evt); } });
		 */
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);

		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				LoadFileEvent(evt);
			}
		});

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		final JCheckBoxMenuItem chckbxmntmFitToScreen = new JCheckBoxMenuItem("Resizble");
		mnView.add(chckbxmntmFitToScreen);

		chckbxmntmFitToScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// modifyResizable(chckbxmntmFitToScreen.isSelected());
			}
		});

	}

	private void LoadFileEvent(ActionEvent evt) {
		JFileChooser filechoose = new JFileChooser();
		filechoose.setDialogTitle("Load Paint Animation");
		filechoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
		filechoose.setAcceptAllFileFilterUsed(true);

		if (filechoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				m.loadImage(filechoose.getSelectedFile().getAbsoluteFile().toString());	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
