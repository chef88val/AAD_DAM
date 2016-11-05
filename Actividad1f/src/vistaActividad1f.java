import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;

public class vistaActividad1f extends JFrame implements ActionListener{

	public JButton btnRotar, btnEspejo;
	private JPanel contentPane;
	 ImageIcon icon;
	private static final String IMG_PATH = "src/images/image01.jpg";
	private static BufferedImage bufferedImage;
	private JPanel pnlRes,pnlOri;
	public JLabel imagen= new JLabel();
	 AffineTransformOp op;
	    AffineTransform tx;
	    int w;// width of buffered image
	    int h;//height of buffered image
	    double angel=Math.PI/2; /** angle should be given in radian and 
	                                        * this program rotates the image in 90 degrees */ 

	/**
	 * Create the frame.
	 */
	public vistaActividad1f() {
		super("Actividad 1f");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnRotar = new JButton("ROTAR");
		btnRotar.setBounds(20, 243, 117, 29);
		btnRotar.addActionListener(this);
		contentPane.add(btnRotar);
		
		 btnEspejo = new JButton("ESPEJO");
		btnEspejo.setBounds(316, 243, 117, 29);
		btnEspejo.addActionListener(this);
		contentPane.add(btnEspejo);
		
		 pnlOri = new JPanel();
		pnlOri.setBounds(20, 51, 162, 168);
		contentPane.add(pnlOri);
		
		 pnlRes = new JPanel();
		pnlRes.setBounds(271, 51, 162, 168);
		contentPane.add(pnlRes);
		
		
		pnlOri.add(imagen);
		File file= new File(IMG_PATH);
		 try {           
	            bufferedImage = ImageIO.read(file); // create a buffered image
	        } catch (IOException ex) {
	            ex.getMessage();
	        }
		 icon = new ImageIcon(bufferedImage);          
		 imagen.setIcon(icon);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		
        //Handle open button action.
		 if(e.getSource()==btnRotar){//Boton abrir fichero
			
			 
		        w= bufferedImage.getWidth();
		        h=bufferedImage.getHeight();
		           
		        //rescale();             
		        
		        icon = new ImageIcon(bufferedImage);          
		        imagen.setIcon(icon);
			 
		     
			
			 
	    }else if (e.getSource()==btnEspejo){
	    	
	    }
 }
}
