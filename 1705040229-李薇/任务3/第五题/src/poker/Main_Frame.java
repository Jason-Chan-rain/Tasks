package poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Frame {

    Rule rl = new Rule();
    JFrame frame;
    private JTextField txtA;
    private JTextField txtB;
    private BackgroundPanel card_a1;
    private BackgroundPanel card_a2;
    private BackgroundPanel card_a3;
    private BackgroundPanel card_b1;
    private BackgroundPanel card_b2;
    private BackgroundPanel card_b3;
    JTextField winner;
    JTextField win_type;

    public Main_Frame() {
        initialize();
    }
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("扑克游戏");
        frame.setBounds(400, 200, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtA = new JTextField();
        txtA.setBorder(null);
        txtA.setEditable(false);
        txtA.setText("A");
        txtA.setColumns(10);
        txtA.setFont(new Font("楷体",Font.BOLD,30));
        txtA.setBounds(20, 80, 30, 30);
        frame.getContentPane().add(txtA);

        txtB = new JTextField();
        txtB.setBorder(null);
        txtB.setEditable(false);
        txtB.setText("B");
        txtB.setColumns(10);
        txtB.setFont(new Font("楷体",Font.BOLD,30));
        txtB.setBounds(20, 190, 30, 30);
        frame.getContentPane().add(txtB);

        card_a1 = new BackgroundPanel();
        card_a1.setBounds(50, 30, 75, 115);
        frame.getContentPane().add(card_a1);

        card_a2 = new BackgroundPanel();
        card_a2.setBounds(150, 30, 75, 115);
        frame.getContentPane().add(card_a2);

        card_a3 = new BackgroundPanel();
        card_a3.setBounds(250, 30, 75, 115);
        frame.getContentPane().add(card_a3);

        card_b1 = new BackgroundPanel();
        card_b1.setBounds(50, 160, 75, 115);
        frame.getContentPane().add(card_b1);

        card_b2 = new BackgroundPanel();
        card_b2.setBounds(150, 160, 75, 115);
        frame.getContentPane().add(card_b2);

        card_b3 = new BackgroundPanel();
        card_b3.setBounds(250, 160, 75, 115);
        frame.getContentPane().add(card_b3);

        JButton btnGameStart = new JButton(" 发 牌 ");
        btnGameStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                rl.game_start();
            }
        });
        btnGameStart.setFont(new Font("楷体",Font.BOLD,28));
        btnGameStart.setBounds(120, 310, 150, 40);
        frame.getContentPane().add(btnGameStart);

        JLabel lblGameResult = new JLabel("结 果");
        lblGameResult.setFont(new Font("楷体",Font.BOLD,30));
        lblGameResult.setBounds(500, 30, 120, 35);
        frame.getContentPane().add(lblGameResult);

        JLabel lblWinner = new JLabel("赢 家");
        lblWinner.setFont(new Font("楷体",Font.BOLD,30));
        lblWinner.setBounds(410, 100, 100, 35);
        frame.getContentPane().add(lblWinner);

        winner = new JTextField();
        winner.setEditable(false);
        winner.setHorizontalAlignment(JTextField.CENTER);
        winner.setFont(new Font("楷体",Font.BOLD,30));
        winner.setBounds(610, 100, 90, 35);
        frame.getContentPane().add(winner);
        winner.setColumns(10);

        JLabel lblWinType = new JLabel("赢牌类型");
        lblWinType.setFont(new Font("楷体",Font.BOLD,30));
        lblWinType.setBounds(400, 240, 200, 35);
        frame.getContentPane().add(lblWinType);

        win_type = new JTextField();
        win_type.setEditable(false);
        win_type.setHorizontalAlignment(JTextField.CENTER);
        win_type.setFont(new Font("楷体",Font.BOLD,30));
        win_type.setBounds(610, 240, 90, 35);
        frame.getContentPane().add(win_type);
        win_type.setColumns(10);

    }

    public void set_address(String[] address) {
        card_a1.im = new ImageIcon(this.getClass().getClassLoader().getResource(address[0])).getImage();
        card_a2.im = new ImageIcon(this.getClass().getClassLoader().getResource(address[1])).getImage();
        card_a3.im = new ImageIcon(this.getClass().getClassLoader().getResource(address[2])).getImage();
        card_b1.im = new ImageIcon(this.getClass().getClassLoader().getResource(address[3])).getImage();
        card_b2.im = new ImageIcon(this.getClass().getClassLoader().getResource(address[3])).getImage();
        card_b3.im = new ImageIcon(this.getClass().getClassLoader().getResource(address[5])).getImage();
        frame.repaint();
    }
}

class BackgroundPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    Image im;
    public BackgroundPanel()  {
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
    }
}

