import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

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

        size_3JMenuItem.addActionListener(e -> System.out.println("[메뉴] Size 3×3 선택"));
        size_4JMenuItem.addActionListener(e -> System.out.println("[메뉴] Size 4×4 선택"));
        size_5JMenuItem.addActionListener(e -> System.out.println("[메뉴] Size 5×5 선택"));
        new_gameJMenuItem.addActionListener(e -> System.out.println("[메뉴] New Game 선택"));
        exitJMenuItem.addActionListener(e -> {
            System.out.println("[메뉴] Exit 선택");
            System.exit(0);          //"중단 확인 팝업"으로 바꿀 자리
        });


        //프래임 설정
        clientJFrame.setSize(700,  500);
        clientJFrame.setLocation(400, 100);
        clientJFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        clientJFrame.setResizable(false);


        menuBar.setBackground(Color.WHITE);
        clientJFrame.add(gamePanel.getView(), BorderLayout.CENTER);

        clientJFrame.setVisible(true);
    }
}
