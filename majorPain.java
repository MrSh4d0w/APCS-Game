import java.awt.*;
import javax.swing.*; 


public class majorPain extends JPanel{
    private Image img;

    public majorPain(String img){
      this(new ImageIcon(img).getImage());
    }

    public majorPain(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
      }

    
      public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
      }
}
