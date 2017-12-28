import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class CarDriver extends JPanel implements KeyListener, Runnable {

    private Image img;
    Toolkit toolkit;
    private int x = 290;
    private int y = 550;
    private int n = 100;
    private int p;

    // Car
    private CarDriver() {
        toolkit = Toolkit.getDefaultToolkit();
        URL url = getClass().getResource("chevy.png");
        img = toolkit.getImage(url);
        setBackground(Color.BLACK);
        Thread game = new Thread(this);
        game.start();
    }


    // Road and environment and car
    public void paint(Graphics g) {
        super.paint(g);
        // left-side grass
        g.setColor(Color.green);
        g.fillRect(0,0,100,700);
        // right-side grass
        g.setColor(Color.green);
        g.fillRect(380,0,500,700);
        // road delimiter lines
        g.setColor(Color.yellow);
        g.drawRect(130,0,220,700);
        // dotted white lines
        g.setColor(Color.white);
        g.fillRect(215, p,15,50);
        g.fillRect(245, p,15,50);
        g.fillRect(215,100 + p,15,50);
        g.fillRect(245,100 + p,15,50);
        g.fillRect(215,200 + p,15,50);
        g.fillRect(245,200 + p,15,50);
        g.fillRect(215,300 + p,15,50);
        g.fillRect(245,300 + p,15,50);
        g.fillRect(215,400 + p,15,50);
        g.fillRect(245,400 + p,15,50);
        g.fillRect(215,500 + p,15,50);
        g.fillRect(245,500 + p,15,50);
        g.fillRect(215,600 + p,15,50);
        g.fillRect(245,600 + p,15,50);
        g.drawImage(img, x, y,this);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_UP:
                n -= 1;
                y--;
                this.repaint();
                break;
            case KeyEvent.VK_DOWN:
                n += 2;
                if (n >= 10) {
                    y++;
                }
                this.repaint();
                break;
            case KeyEvent.VK_LEFT:
                x -= 5;
                this.repaint();
                break;
            case KeyEvent.VK_RIGHT:
                x += 5;
                this.repaint();
                break;
            case KeyEvent.VK_S:
                y += 50;
                this.repaint();
                break;
            case KeyEvent.VK_W:
                y -= 50;
                this.repaint();
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
        // not implemented
    }

    public void keyReleased(KeyEvent e) {
        // not implemented
    }

    public void run() {
        repaint();
        for (p = 0; p <= 100; p += 10) {
            repaint();
            try {
                Thread.sleep(n);
            } catch (Exception e) {
                System.out.println("");
            }

            if (p == 100) {
                p = 0;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel header = new JLabel("Sc0res");
        header.setBounds(400, 600, 50, 20);
        header.setForeground(Color.red);
        frame.add(header);
        frame.setTitle("            Rock'n'Roll");
        JLabel footer = new JLabel("Use arrows and 'W', 'S' keys");
        footer.setBounds(20,650, 200, 20);
        footer.setForeground(Color.blue);
        frame.add(footer);
        frame.setLayout(null);
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CarDriver chevy = new CarDriver();
        chevy.setBounds(0,0, 500, 650);
        frame.add(chevy);
        frame.addKeyListener(chevy);
    }
}
