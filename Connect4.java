import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.util.*;
import java.awt.Font;

class Connect4 extends JComponent {
    int[][] pieces = new int[6][7];
    int win = 0;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle board = new Rectangle(0, 0, 700, 600);
        g2.setColor(Color.BLUE);
        g2.draw(board);
        g2.fill(board);

        //Colors 0 = Blank, 1 = Green, 2 = Red
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                if(pieces[i][j] == 0) {
                    Ellipse2D.Double circle = new Ellipse2D.Double(100 * j, 100 * i, 100, 100);
                    g2.setColor(Color.WHITE);
                    g2.draw(circle);
                    g2.fill(circle);
                } else if(pieces[i][j] == 1) {
                    Ellipse2D.Double circle = new Ellipse2D.Double(100 * j, 100 * i, 100, 100);
                    g2.setColor(Color.YELLOW);
                    g2.draw(circle);
                    g2.fill(circle);
                } else if(pieces[i][j] == 2) {
                    Ellipse2D.Double circle = new Ellipse2D.Double(100 * j, 100 * i, 100, 100);
                    g2.setColor(Color.RED);
                    g2.draw(circle);
                    g2.fill(circle);
                }
            }
        }
        if(win == 1)
        {
            g2.setColor(Color.BLACK);
            String str ="Yellow Player WINS ! ";// 200,650
            Font stringFont = new Font( "SansSerif", Font.PLAIN, 30 );
            g2.setFont( stringFont );
            g2.drawString( str, 225, 650 );

        }
        if(win == 2)
        {
            g2.setColor(Color.BLACK);
            String str ="Red Player WINS ! ";// 200,650
            Font stringFont = new Font( "SansSerif", Font.PLAIN, 30 );
            g2.setFont( stringFont );
            g2.drawString( str, 225, 650 );

        }
    }


    private int xCoord;
    private int yCoord;
    private boolean turn = true;
    public void setColor(int x, int y)
    {
        xCoord = x;
        yCoord = y;
        int stop = 0;
        if(turn == true)
        {
            for(int i = 5; i >= 0; i--)
            {
                if(pieces[i][xCoord/100] == 0 && stop == 0)
                {
                    pieces[i][xCoord/100]= 1;
                    stop = 1;
                }
            }

            turn = false;
        }
        else
        if(turn == false)
        {
            for(int i = 5; i >= 0; i--)
            {
                if(pieces[i][xCoord/100] == 0 && stop == 0)
                {
                    pieces[i][xCoord/100] = 2;
                    stop = 1;
                }
            }
            turn = true;
        }
        repaint();
        for(int i = 5; i >= 0; i--)//HORIZONTAL
        {
            for(int j = 3; j >= 0; j--)
            {
                if(pieces[i][j] == 1 && 1 == pieces[i][j+1]&& 1 == pieces[i][j+2] && 1 == pieces[i][j+3])
                {
                    win = 1;
                }
                if(pieces[i][j] == 2 && 2 == pieces[i][j+1]&& 2 == pieces[i][j+2] && 2 == pieces[i][j+3])
                {
                    win = 2;
                }
            }
        }
        for(int i = 2; i >= 0; i--)//VERTICAL
        {
            for(int j = 6; j >= 0; j--)
            {
                if(pieces[i][j] == 1 && 1 == pieces[i+1][j]&& 1 == pieces[i+2][j] && 1 == pieces[i+3][j])
                {
                    win = 1;
                }
                if(pieces[i][j] == 2 && 2 == pieces[i+1][j]&& 2 == pieces[i+2][j] && 2 == pieces[i+3][j])
                {
                    win = 2;
                }
            }
        }
        for(int i = 3; i <= 5; i++)
        {
            for(int j = 0; j <= 3; j++)
            {
                if(pieces[i][j] == 1 && 1 == pieces[i-1][j+1]&& 1 == pieces[i-2][j+2] && 1 == pieces[i-3][j+3])
                {
                    win = 1;
                }
                if(pieces[i][j] == 2 && 2 == pieces[i-1][j+1]&& 2 == pieces[i-2][j+2] && 2 == pieces[i-3][j+3])
                {
                    win = 2;
                }
            }
        }
        for(int i = 0; i <= 3; i++)
        {
            for(int j = 0; j <= 3; j++)
            {
                if(pieces[i][j] == 2 && 2 == pieces[i+1][j+1]&& 2 == pieces[i+2][j+2] && 2 == pieces[i+3][j+3])
                {
                    win = 2;
                }
                if(pieces[i][j] == 1 && pieces[i+1][j+1] == 1 && pieces[i+2][j+2] == 1 && pieces[i+3][j+3] == 1)
                {
                    win = 1;
                }
            }
        }
        repaint();
    }
    public static void main(String[] args)
    {

        JFrame frame = new JFrame();
        frame.setSize(715,700);
        Connect4 myLittleCircle = new Connect4();
        frame.setTitle("Devryan Singh");
        Connect4 box = new Connect4();



        class MouseClickListener implements MouseListener
        {
            public void mousePressed(MouseEvent event)
            {}
            public void mouseExited(MouseEvent event)
            {}
            public void mouseEntered(MouseEvent event)
            {}
            public void mouseReleased(MouseEvent event)
            {}
            public void mouseClicked(MouseEvent event)
            {
                int x = event.getX(); //returns x coordinate of the location the mouse click on
                int y = event.getY();
                myLittleCircle.setColor(x,y);
            }
        }
        MouseClickListener listener = new MouseClickListener();
        myLittleCircle.addMouseListener(listener);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(box);
        frame.add(myLittleCircle);
        frame.setVisible(true);

    }
}











