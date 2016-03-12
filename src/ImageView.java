import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageView extends JPanel {

	Model m;
	ImageModel imgModel;
	CardLayout c;
	GridElement gridElement;
	ListElement listElement;

	public ImageView(ImageModel imgModel, Model model) {
		m = model;
		this.imgModel = imgModel;
		c = new CardLayout(0, 0);
		setLayout(c);
		
		gridElement = new GridElement(m, imgModel);
		add(gridElement, "grid");
		this.setPreferredSize(gridElement.getPreferredSize());
		
		listElement = new ListElement(m, imgModel);
		add(listElement, "list");
		//this.setPreferredSize(listElement.getPreferredSize());
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				createNewFrame();
			}
		});
		
	}

	protected void createNewFrame() {
        System.out.println("Hi!");
        ImageDisplay imgDisplay = new ImageDisplay(imgModel.image);
        imgDisplay.setVisible(true);
	}

	public void convertGridElements() {
		System.out.println(m.isGrid);
		if (m.isGrid) {
			this.setPreferredSize(gridElement.getPreferredSize());
			c.next(this);
		} else {
			this.setPreferredSize(listElement.getPreferredSize());
			c.next(this);
		}
		this.validate();
		// m.changeCardType();
	}

}
