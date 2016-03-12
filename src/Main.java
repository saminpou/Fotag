import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	public JScrollPane scrollGrid;
	public GridDisplay gridDisplay;
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
		this.setMinimumSize(new Dimension(300, 200));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Topbar topbar = new Topbar(m);
		contentPane.add(topbar, BorderLayout.NORTH);

		gridDisplay = new GridDisplay(m);
		scrollGrid = new JScrollPane(gridDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollGrid, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);

		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				LoadFileEvent(evt);
			}
		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				modifyResizable(e.getComponent().getWidth());
			}
		});

	}

	public void dispose() {
		m.saveFile();
		super.dispose();
	}

	private void LoadFileEvent(ActionEvent evt) {
		JFileChooser filechoose = new JFileChooser();
		filechoose.setDialogTitle("Load Paint Animation");
		filechoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
		filechoose.setAcceptAllFileFilterUsed(true);
		filechoose.setMultiSelectionEnabled(true);

		if (filechoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File files[] = filechoose.getSelectedFiles();
			for (File f : files) {
				try {
					m.loadImage(f.getAbsoluteFile().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
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

	public void modifyResizable(int width) {
		if (m.isGrid)
			this.setMinimumSize(new Dimension(300, 200));
		else
			this.setMinimumSize(new Dimension(450, 200));
		m.calculateNumColumns(width);
	}

}
