import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

//창 닫기
class EventHandler implements WindowListener {
    public void windowOpened(WindowEvent w) {}
    public void windowClosing(WindowEvent w) {
        System.exit(0);
    }
    public void windowClosed(WindowEvent w) {}
    public void windowIconified(WindowEvent w) {}
    public void windowDeiconified(WindowEvent w) {}
    public void windowActivated(WindowEvent w) {}
    public void windowDeactivated(WindowEvent w) {}
}

public class MainFrame {
    JFrame clientJFrame = new JFrame("8-Puzzle\n");

    JMenuBar mainJMenuBar = new JMenuBar();     //메뉴바
    JMenu menuJMenu = new JMenu("Menu");
    JMenuItem exitJMenuItem = new JMenuItem("Exit");
    JMenuItem new_gameJMenuItem = new JMenuItem("New Game");
    JMenu sizeJMenu = new JMenu("Size");
    JMenuItem size_3JMenuItem = new JMenuItem("3*3");
    JMenuItem size_4JMenuItem = new JMenuItem("4*4");
    JMenuItem size_5JMenuItem = new JMenuItem("5*5");

    JPanel[] puzzleJPanels = new JPanel[3];      //퍼즐 패널 부분
    JButton[][] bingo_numJButtons = new JButton[7][7];


    JPanel chatJPanel = new JPanel();           //채팅 부분
    JTextPane chat_historytJTextPane = new JTextPane();
    JScrollPane chat_scrollPane = new JScrollPane(chat_historytJTextPane);

    JPanel inputJPanel = new JPanel();
    JTextField input_locJTextField = new JTextField("");
    JButton input_sendJButton = new JButton("전송");
    JTextArea infJTextArea = new JTextArea("현재 대전 대기 중인 상대가 없습니다.\n잠시후 다시 접속하세요.");

    public MainFrame() {
        //메뉴바 설정
        sizeJMenu.add(size_3JMenuItem);
        sizeJMenu.add(size_4JMenuItem);
        sizeJMenu.add(size_5JMenuItem);

        menuJMenu.add(sizeJMenu);
        menuJMenu.add(new_gameJMenuItem);
        menuJMenu.addSeparator();
        menuJMenu.add(exitJMenuItem);

        mainJMenuBar.add(menuJMenu);
        clientJFrame.setJMenuBar(mainJMenuBar);

//        size_3JMenuItem.addActionListener(menubarListener);
//        size_4JMenuItem.addActionListener(menubarListener);
//        size_5JMenuItem.addActionListener(menubarListener);
//        new_gameJMenuItem.addActionListener(menubarListener);
//        exitJMenuItem.addActionListener(menubarListener);

        //채팅 부분 설정
        inputJPanel.setLayout(new BorderLayout());
        inputJPanel.add(input_locJTextField, "Center");
        inputJPanel.add(input_sendJButton, "East");

//        input_sendJButton.addActionListener(sendlListener);
//        input_locJTextField.addKeyListener(enter_key);

        chatJPanel.setLayout(new BorderLayout());
        chatJPanel.add(infJTextArea, "North");
        chatJPanel.add(chat_scrollPane, "Center");
        chatJPanel.add(inputJPanel, "South");  //West, East

        chat_historytJTextPane.setEditable(false);
        infJTextArea.setEditable(false);

        //빙고 부분 설정
        for(int x=0;x<7;x++){
            for(int y=0;y<7;y++){
                bingo_numJButtons[x][y] = new JButton("n");
                bingo_numJButtons[x][y].setFont(new Font("맑은고딕", Font.PLAIN, 20));
                bingo_numJButtons[x][y].setBackground(Color.WHITE);
//                bingo_numJButtons[x][y].addActionListener(bingoListener);
            }
        }

        for(int x=0;x<3;x++){
            puzzleJPanels[x] = new JPanel();
            puzzleJPanels[x].setLayout(new GridLayout(3+x*2, 3+x*2));
            puzzleJPanels[x].setPreferredSize(new Dimension(500, 500));
            puzzleJPanels[x].setBackground(Color.lightGray);
        }

        //프래임 설정
        clientJFrame.setSize(700,  500);
        clientJFrame.setLocation(400, 100);

        clientJFrame.setVisible(true);
        clientJFrame.setResizable(false);

        clientJFrame.addWindowListener(new EventHandler());

        mainJMenuBar.setBackground(Color.WHITE);

        input_locJTextField.requestFocus();    //포커스

//        chat_history_update(chat_historytJTextPane, "Welcome To Online Bingo Game!!\n");
    }
    //메뉴바 리스너
    ActionListener menubarListener = new ActionListener() {   //메뉴바
        public void actionPerformed(ActionEvent e) {
        }
    };

}
