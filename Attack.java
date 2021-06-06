import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;

public class Attack extends JPanel implements ActionListener {

    private String c, e;
    private BufferedImage img;
    private Canvas canvas;
    private BufferStrategy strategy;
    private BufferedImage background;
    private Graphics2D backgroundGraphics;
    private Graphics2D graphics;
    private int scale = 1;
    private final int[][] character1SpriteSheet = { { 0, 0, 1920, 1080 }, { 1920, 0, 1920, 1080 },
            { 3840, 0, 1920, 1080 }, { 5760, 0, 1920, 1080 }, { 7680, 0, 1920, 1080 }, { 9600, 0, 1920, 1080 },
            { 11520, 0, 1920, 1080 }, { 13440, 0, 1920, 1080 }, { 15360, 0, 1920, 1080 }, { 17280, 0, 1920, 1080 },
            { 19200, 0, 1920, 1080 }, { 21120, 0, 1920, 1080 }, { 23040, 0, 1920, 1080 }, { 24960, 0, 1920, 1080 },
            { 26880, 0, 1920, 1080 }, { 28800, 0, 1920, 1080 }, { 30720, 0, 1920, 1080 }, { 32640, 0, 1920, 1080 },
            { 34560, 0, 1920, 1080 }, { 36480, 0, 1920, 1080 }, { 38400, 0, 1920, 1080 }, { 40320, 0, 1920, 1080 },
            { 42240, 0, 1920, 1080 }, { 44160, 0, 1920, 1080 } };
    private int i = 0;
    private GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public final BufferedImage create(final int width, final int height, final boolean alpha) {
        return config.createCompatibleImage(width, height, alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            i++;
            if (i == character1SpriteSheet.length) {i = 0;}
            revalidate();
            repaint();
        }
    };

    public Attack(String c, String e) {
        Timer timer = new Timer(100, actionListener);
        timer.setInitialDelay(0);
        timer.start();
        this.c = c;
        this.e = e;
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (c) {
            case "Character1":
                g = animateCharacter1(g);
                break;
        }
    }

    public Graphics animateCharacter1(Graphics g) {
        getImg("Character1_Attack");
        Image subSprite = null;
        subSprite = img.getSubimage(character1SpriteSheet[i][0], character1SpriteSheet[i][1],
                character1SpriteSheet[i][2], character1SpriteSheet[i][3]);
        g.drawImage(subSprite, 0, 0, null);
        return g;
    }

    private void getImg(String name) {
        try {
            img = ImageIO.read(new File("images/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
