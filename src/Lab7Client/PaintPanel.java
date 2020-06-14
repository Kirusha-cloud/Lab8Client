package Lab7Client;

import Flat.Flat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PaintPanel extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private HashMap<Shape, String> data = new HashMap<>();
    private static PaintPanel paintPanel;
    private HashMap<String, Color> colors = new HashMap<>();
    private Timer timer = new Timer(3000, e -> repaint());
    private boolean flag = false;
    private double angle = 0;
    private Timer animationTimer = new Timer(35, e -> {
        repaint();
        angle += 0.15;
    });


    private PaintPanel() {
        setOpaque(false);
        timer.start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseEntered(e);
                for (Shape x : shapes) {
                    if (x.contains(e.getPoint())) {
                        WindowsManager.getVisualWindow().getTextArea().setText("   Информация о выбранном объекте: " + "\n" + "    " + data.get(x));
                        if (WindowsManager.getVisualWindow().changeFlag()) {
                            ChangeForm changeForm = new ChangeForm();
                            changeForm.setVisible(true);
                            changeForm.pushData(data.get(x));
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (flag) {
                int width = getWidth();
                int height = getHeight();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3f));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                double x = 0.5 * width;
                double y = 0.5 * height;
                double r = 0.75 * Math.min(x, y);
                g2d.setColor(Color.ORANGE);
                x += r * Math.cos(angle);
                y += r * Math.sin(angle);
                r = Math.max(0.1 * r, 5);
                Rectangle2D.Double a = (Rectangle2D.Double) square(x, y, r);
                g2d.fill(a);
            }
         else {
            super.paintComponent(g);
            HashMap<String, ArrayList<Integer>> objectOwners = DataManager.getObjectOwners();
            for (Map.Entry<String, ArrayList<Integer>> entry : objectOwners.entrySet()) {
                Random random1 = new Random();
                Random random2 = new Random();
                int a = random1.nextInt(100);
                int b = random2.nextInt(100);
                String login = entry.getKey();
                ArrayList<Integer> list = entry.getValue();
                if (!colors.containsKey(login)){
                    colors.put(login, new Color (a, b, login.hashCode() % 100));
                }
                WindowsManager.getVisualWindow().getColorLabel().setOpaque(true);
                WindowsManager.getVisualWindow().getColorLabel().setBackground(colors.get(AuthorizationFrame.getCurrentUser()));
                WindowsManager.getVisualWindow().getColorLabel().setForeground(Color.white);
                for (Integer z : list) {
                    Flat flat = findById(z);
                    drawFlat(g, flat, login, colors.get(login));
                }
            }
        }
    }

    private void drawFlat(Graphics g, Flat flat, String login, Color color) {
        try {
            if (flat != null) {
                int x = flat.getCoordinates().getX();
                int y = (int) Math.round(flat.getCoordinates().getY());
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(color);
                Ellipse2D ellipse2D = new Ellipse2D.Double(x % getWidth(), y % getHeight(), flat.getNumberOfRooms() + 40, flat.getNumberOfRooms() + 40);
                shapes.add(ellipse2D);
                data.put(ellipse2D, flat.toString());
                g2.fill(ellipse2D);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
            removeAll();
            revalidate();
            repaint();
        }
    }

    public void clearPanel() {
        removeAll();
        PaintPanel.getPaintPanel().revalidate();
        PaintPanel.getPaintPanel().repaint();
        animationTimer.start();
    }


    private Flat findById(int id) {
        for (Flat x : DataManager.getPColl()) {
            if (x.getId().equals(id))
                return x;
        }
        return null;
    }

    public static PaintPanel getPaintPanel() {
        if (paintPanel == null) {
            paintPanel = new PaintPanel();
            return paintPanel;
        } else return paintPanel;
    }

    public void pushAnimate() {
        this.flag = true;
    }
    public void stopAnimate(){
        this.flag = false;
        animationTimer.stop();
    }
    private Shape square(double x, double y, double r) {
        return new Rectangle2D.Double(x - r, y - r, 2 * r, 2 * r);
    }
}
