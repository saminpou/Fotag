import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class Topbar extends JPanel implements Observer {

	Model m;
	JLabel lblNewLabel;
	JToggleButton gridButton;
	JToggleButton listButton;
	public Topbar(Model model) {
		m = model;
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		m.addObserver(this);
		//setPreferredSize(new Dimension(500,75));
		lblNewLabel = new JLabel("Fotag!");
		lblNewLabel.setMinimumSize(new Dimension(10, 10));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		
		RatingSelector ratingSelector = new RatingSelector(m);
		
		gridButton = new JToggleButton("<html>╔═╦═╗<br>╠═╬═╣<br>╚═╩═╝</html>");
		gridButton.setFont(new Font("Courier", Font.PLAIN, 14));
		gridButton.setMargin(new Insets(0, 0, 0, 0));
		gridButton.setVerticalAlignment(SwingConstants.TOP);
		gridButton.setHorizontalAlignment(SwingConstants.LEFT);
		gridButton.setHorizontalTextPosition(SwingConstants.LEFT);
		gridButton.setVerticalTextPosition(SwingConstants.TOP);
		gridButton.setPreferredSize(new Dimension(50,50));
		gridButton.setMaximumSize(new Dimension(50,50));
		gridButton.setFocusPainted(false);
		this.gridButton.setSelected(m.isGrid);
		
		gridButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton btn = (JToggleButton) e.getSource();
				updateButton();
			}
		});
		
		listButton = new JToggleButton("<html>╔═╗  <br>╠═╣﹌<br>╚═╝﹌</html>");
		listButton.setVerticalTextPosition(SwingConstants.TOP);
		listButton.setVerticalAlignment(SwingConstants.TOP);
		listButton.setPreferredSize(new Dimension(50, 50));
		listButton.setMaximumSize(new Dimension(50, 50));
		listButton.setMargin(new Insets(0, 0, 0, 0));
		listButton.setHorizontalTextPosition(SwingConstants.LEFT);
		listButton.setHorizontalAlignment(SwingConstants.LEFT);
		listButton.setFont(new Font("Courier", Font.PLAIN, 14));
		listButton.setFocusPainted(false);
		this.listButton.setSelected(!m.isGrid);
		listButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JToggleButton btn = (JToggleButton) e.getSource();
				updateButton();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(gridButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ratingSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
								.addComponent(ratingSelector, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(gridButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(listButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	@Override
	public void update(Observable o, Object arg) {
		if (m.columns <= 2){
			lblNewLabel.hide();
		}else{
			lblNewLabel.show();;
		}
		if (m.isGrid){
			this.listButton.setSelected(false);
		}else{
			this.gridButton.setSelected(false);
		}
	}
	
	public void updateButton(){
		m.changeCardType();
		if (m.isGrid){
			this.listButton.setSelected(false);
		}else{
			this.gridButton.setSelected(false);
		}	
	}	
}
