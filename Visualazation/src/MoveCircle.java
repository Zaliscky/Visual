import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Ivan on 13.11.2016.
 */
public class MoveCircle extends JPanel {

    private Point rectPosition = new Point();
    public static  int RECT_SIZE = 50;
    public static boolean  grow = true;
    public Point getRectPosition() {
        return rectPosition;
    }

    public void setRectPosition(Point position) {
        this.rectPosition = position;
        repaint();
    }

    public MoveCircle() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        actionMap.put("left", new MoveAction(this, MoveAction.Direction.LEFT));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        actionMap.put("right", new MoveAction(this, MoveAction.Direction.RIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(rectPosition.x, rectPosition.y, RECT_SIZE, RECT_SIZE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    private static class MoveAction extends AbstractAction {
        public enum Direction{
            LEFT, RIGHT //, UP, DOWN
        };

        private Direction direction;
        private MoveCircle panel;

        public MoveAction(MoveCircle panel, Direction direction) {
            this.direction = direction;
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Point position = panel.getRectPosition();
            switch(direction) {
                case LEFT:
                    position.x -= 3;
                    if (RECT_SIZE >= 200) {
                        grow = false;
                    }
                    if (RECT_SIZE <= 20) {
                        grow = true;
                    }

                    if (grow) {
                        RECT_SIZE += 3;

                    } else {
                        RECT_SIZE -= 3;

                    }
                    break;
                case RIGHT:
                    position.x += 3;
                    if (RECT_SIZE >= 200) {
                        grow = false;
                    }
                    if (RECT_SIZE <= 20) {
                        grow = true;
                    }

                    if (grow) {
                        RECT_SIZE += 3;

                    } else {
                        RECT_SIZE -= 3;

                    }
                    break;

            }

            panel.setRectPosition(position);
        }
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("Move Circle");
        frame.getContentPane().add(new MoveCircle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowUI();
            }
        });
    }
}