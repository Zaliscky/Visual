import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Ivan on 30.10.2016.
 */
public class AutoCircle extends Applet implements Runnable {
    int x = 100, y = 25, r = 20;
    private Point last;
    private boolean dragging = false;
    int dx = 11, dy = 7;

    Thread animator;

    volatile boolean pleaseStop;


    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x - r, y - r, r * 2, r * 2);
    }
    private int x1;
    private int y2;
    private int width;
    private int height;

    public void animate() {


        Rectangle bounds = getBounds();
        if ((x - r + dx < 0) || (x + r + dx > bounds.width))
            dx = -dx;

        if ((y - r + dy < 0) || (y + r + dy > bounds.height))
            dy = -dy;



       x += dx + 1;
        dy = 1;
        y = 25;

        repaint();
    }


    public void mouseDragged(MouseEvent m) {
        int dx = m.getX() - last.x;
        int dy = m.getY() - last.y;
        if (dragging) {
            x1 += dx;
            y2 += dy;
        } else {
            width += dx;
            height += dy;
        }
        last = m.getPoint();
        repaint();
    }


    public void animate2() {


        Rectangle bounds = getBounds();
        if ((x - r + dx < 0) || (x + r + dx > bounds.width))
            dx = -dx;

        if ((y - r + dy < 0) || (y + r + dy > bounds.height))
            dy = -dy;


        x += dx + 1;
        dy =  dy -5;
        y = 150;
        r = r +1;

        repaint();
    }


    public void run() {
        while (!pleaseStop) {
            //animate();
            animate2();
            // Update and request redraw
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
            } // Ignore interruptions


        }


    }

    public void run2() {
        while (!pleaseStop) {
            animate2();
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
            }
        }
    }



    public void start() {
        animator = new Thread(this);
        pleaseStop = false;
        animator.start();
    }



    public void stop() {

        pleaseStop = true;

    }

   public static void main(String[] args) {
        AutoCircle _ = new AutoCircle();
        _.run();
        _.run2();
    }
}