import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {
    JFrame clientJFrame = new JFrame("8-Puzzle");

    JMenuBar menuBar = new JMenuBar();     //메뉴바
        JMenu menuJMenu = new JMenu("Menu");
        JMenuItem exitJMenuItem = new JMenuItem("Exit");
        JMenuItem new_gameJMenuItem = new JMenuItem("New Game");
        JMenu sizeJMenu = new JMenu("Size");
            JMenuItem size_3JMenuItem = new JMenuItem("3×3");
            JMenuItem size_4JMenuItem = new JMenuItem("4×4");
            JMenuItem size_5JMenuItem = new JMenuItem("5×5");

    GamePanel gamePanel;

    public MainFrame(GameController controller) {

        gamePanel = new GamePanel(controller);
        controller.setPanel(gamePanel);

        //메뉴바 설정
        sizeJMenu.add(size_3JMenuItem);
        sizeJMenu.add(size_4JMenuItem);
        sizeJMenu.add(size_5JMenuItem);

        menuJMenu.add(sizeJMenu);
        menuJMenu.add(new_gameJMenuItem);
        menuJMenu.addSeparator();
        menuJMenu.add(exitJMenuItem);

        menuBar.add(menuJMenu);
        clientJFrame.setJMenuBar(menuBar);

        // 메뉴 선택 → 컨트롤러의 매칭되는 메서드 호출 (화면은 전달만)
        size_3JMenuItem.addActionListener(e -> controller.startGame(3));
        size_4JMenuItem.addActionListener(e -> controller.startGame(4));
        size_5JMenuItem.addActionListener(e -> controller.startGame(5));

        new_gameJMenuItem.addActionListener(e -> controller.onNewGame());
        exitJMenuItem.addActionListener(e -> controller.onCloseRequest());

        //프레임 설정
        clientJFrame.setSize(700,  500);
        clientJFrame.setLocation(400, 100);
        clientJFrame.setResizable(false);

        // 창 닫기(X): 바로 종료하지 않고 중단 확인 팝업
        clientJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        clientJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.onCloseRequest();
            }
        });

        menuBar.setBackground(Color.WHITE);
        clientJFrame.add(gamePanel.getView(), BorderLayout.CENTER);

        clientJFrame.setVisible(true);
    }
}