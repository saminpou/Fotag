import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelContainer extends JPanel implements Observer{

	
	ArrayList <ImageView> images;
	public JScrollPane scrollList;
	public JScrollPane scrollGrid;
	public GridDisplay gridDisplay;
	ListDisplay listDisplay;
	Model m;
	public PanelContainer(Model model) {
		m = model;
		model.addObserver(this);
		images = new ArrayList <ImageView>();
		populateImages();
		setLayout(new CardLayout(0, 0));
		
		gridDisplay = new GridDisplay(m, images);
		scrollGrid = new JScrollPane(gridDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollGrid, BorderLayout.CENTER);
		
		listDisplay = new ListDisplay(m);
		scrollList = new JScrollPane(listDisplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollList, BorderLayout.CENTER);
	}
	
	public void populateImages(){
		for (int x = 0; x < m.DisplayedImageCollectionModel.size(); x++){
			ImageView i = new ImageView(x, m.DisplayedImageCollectionModel.get(0), m);
			images.add(i);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (((String)arg).equals("newImage")){
			images.add(new ImageView(m.DisplayedImageCollectionModel.size() - 1, m.DisplayedImageCollectionModel.get(m.DisplayedImageCollectionModel.size() - 1), m));
			gridDisplay.addImage();
			gridDisplay.updateUI();
		}
	}
}
//scroll.setSize(new Dimension (100, 100));
//scroll.setBounds(0, 0, 40, 40);