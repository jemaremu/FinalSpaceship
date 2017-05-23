import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.applet.*;
import java.awt.event.*;

// html 700 x 700 px
/**
 * The only thing left to do is to destroy the spaceship once its shot
 *	and make to work the html file, since we are using more than one class there need to be changes 
 *	in the html file, right now I am working on it
 */
public class TheSpaceGame extends daApplet implements KeyListener, ActionListener {
	private int DestroyerX = 480, DestroyerY = 250;// the x and y of the position of the player
	private int SpaceShipX =100, SpaceShipY =100; //the x and y of the position of the spaceship enemy 
	private int shotX = 800 ; 
	private int shotY = 800;

	protected Image shipPlayer;
	protected Image spaceship;
	protected Image shots;
	protected Image backg;
	protected Image backg2; //...........
	protected Image explosion;
	protected boolean keyPrested;

	protected Button start;
	protected Button quit;
	private boolean started;
	private boolean quited;
	private boolean shipDestroyed;
	//private boolean explosion;

	protected Dimension d;

	/**
	 * Paint was partitioned in if statements
	 */
	public void paint(Graphics g){
		spaceship = getImage(getCodeBase(), "spaceship.gif");
		shipPlayer = getImage(getCodeBase(), "ship.png");
		backg = getImage(getCodeBase(), "outer_space.jpg");
		//backg2 = getImage(getCodeBase(), "planet.png");;
		shots = getImage(getCodeBase(), "shots.png");
		d = getSize();

		/* IF START BUTTON IS PRESED IS GOING TO PAINT THE WHOLE GAME */
		if(started == true && quited == false){
			g.setColor(Color.white);
			g.fillRect(0,0,d.width, d.height);
			g.setFont(new Font("Sans-serif", Font.BOLD, 24));
			g.setColor(new Color(255, 0, 0));

			g.setColor(Color.white);
			g.fillRect(0,0,d.width, d.height);
			g.setFont(new Font("Sans-serif", Font.BOLD, 24));
			g.drawImage(backg , 0, 0, this);
			//g.drawImage(backg2 , 0, 0, this);

			g.setColor(new Color(255, 0, 0));
			g.drawString("Lets Battle!!", 250, 350);

			paintSpaceship(spaceship, g);
			paintShip(shipPlayer, g);

			explosion = getImage(getCodeBase(), "explosion.png");
			
			/* boolean condition */
			if(shotX > 0 && shipDestroyed == false){
				shotX = shotX -10;
				//if(shotX != SpaceShipX)
				paintShot(shots, g);
			}
			
			/**/
			if(shipDestroyed == true){
				g.drawImage(explosion, SpaceShipX, SpaceShipY, this);
				//paintShot(shots, g);
				int delay = 0;
				while(delay < 1000){
					//g.drawImage(explosion, shotX, shotY, this);
					delay++;
				}
				shipDestroyed = false;
			}
			/**/

		}
		/* IF QUIT BUTTOM IS PRESSED IS GOING TO PAINT THE MY LITTLE PONY DISSAPROVAL IMAGE */
		else if(quited == true && started == false){
			g.setColor(Color.white);
			g.fillRect(0,0,d.width, d.height);
			g.setFont(new Font("Sans-serif", Font.BOLD, 24));
			g.setColor(new Color(255, 0, 0));
			g.drawString("You abandoned us!!!", 200, 100);
			Image mlp = getImage(getCodeBase(), "mlp.jpg");
			g.drawImage(mlp, 10, 200, this);
		}
		/* ELSE NO BUTTOM HAVE BEEN PRESSED */
		else{
			g.setColor(Color.white);
			g.fillRect(0,0,d.width, d.height);
			g.setFont(new Font("Sans-serif", Font.BOLD, 24));
			g.setColor(new Color(255, 0, 0));
			g.drawString("Press start!!", 200, 250);
		}

	}

	public void paintSpaceship(Image space, Graphics h){
		h.drawImage(space , SpaceShipX, SpaceShipY, this);
	}

	public void paintShip(Image ship, Graphics i){
		i.drawImage(ship, DestroyerX, DestroyerY, this);
	}

	public void paintShot(Image shot, Graphics j){
		j.drawImage(shot, shotX, shotY, this);
		repaint();
	}


	/**
	 * MOST CHANGED WERE MADE HERE IN ORDER TO MAKE THE BUTTOMS WORK
	 * outside keyPressed method is abstract, reason why is empty
	 */
	public void init() {
		addKeyListener(this);
		shipDestroyed = false;
		start = new Button("Start :)");
		quit = new Button("Quit :(");
		start.addActionListener(this);
		start.addKeyListener(new KeyAdapter() {
			//keyPressed method was moved here in order to make it work once the start button is pressed
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				// Destroyer Cases
				switch( keyCode ) { 
				case KeyEvent.VK_UP: if( DestroyerY>0 ){ //when up key is pressed and the position of the player is not on the edge
					DestroyerY=DestroyerY-19;
					repaint();
				}
				break;
				case KeyEvent.VK_DOWN: if( DestroyerY<480 ){//when down key is pressed and the position of the player is not on the edge
					DestroyerY=DestroyerY+19;
					repaint();
				}
				break;
				case KeyEvent.VK_LEFT:  if( DestroyerX>250 ){
					DestroyerX=DestroyerX-15;
					repaint();
				}
				break;
				case KeyEvent.VK_RIGHT: if( DestroyerX<480 ){
					DestroyerX=DestroyerX+15;
					repaint();
				}
				break;

				}


				//Spaceship
				switch( keyCode ) { 
				case KeyEvent.VK_W: if( SpaceShipY>0 ){ //when up key is pressed and the position of the player is not on the edge
					SpaceShipY=SpaceShipY-19;
					repaint();
				}
				break;
				case KeyEvent.VK_S: if( SpaceShipY<480 ){//when down key is pressed and the position of the player is not on the edge
					SpaceShipY=SpaceShipY+19;
					repaint();
				}
				break;
				case KeyEvent.VK_A:  if( SpaceShipX>20 ){
					SpaceShipX=SpaceShipX-15;
					repaint();
				}
				break;
				case KeyEvent.VK_D: if( SpaceShipX<250 ){
					SpaceShipX=SpaceShipX+15;
					repaint();
				}
				break;
				}

				//Shot
				switch( keyCode ){
				case KeyEvent.VK_SPACE: 
					shotX = DestroyerX - 20;
					shotY = DestroyerY + 40;
					repaint();

					/**/
					if(isShipDestroyed() == true){
						System.out.println("ship is destroyed.");
						//shipDestroyed = true;
						repaint();
						shipDestroyed = true;
					}
					/**/


					break;
				}
			}
		});

		quit.addActionListener(this);
		add(start, BorderLayout.CENTER);
		add(quit, BorderLayout.CENTER);

		started = false;
		quited = false;
	}

	public void actionPerformed(ActionEvent e){
		Button source = (Button)e.getSource();
		System.out.println(source.getLabel());
		if(source.getLabel() == "Start :)"){
			System.out.println("inside start");
			started = true;
			quited = false;
			repaint();
		}
		if(source.getLabel() == "Quit :("){
			System.out.println("inside quit");
			quited = true;
			started = false;
			repaint();
		}
	}

	public void keyPressed(KeyEvent e) {

	} 

	public void keyReleased(){

	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void moveShip(){

	}
	
	private boolean isShipDestroyed(){
		System.out.println("shotY: "+ shotX + " shotX: " + shotX);
		System.out.println("SpaceShipY: "+ SpaceShipY + " SpaceShipX: " + SpaceShipX);
		
		if(shotY == SpaceShipY && shotX == SpaceShipX){
			return true;
		}
		else if((shotY > (SpaceShipY)) && (shotY <= (SpaceShipY+54))){
			return true;
		}
		else
			return false;
	}
}







