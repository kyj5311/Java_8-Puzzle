import javax.swing.*;
import java.awt.*;

public class GamePanel {

    JPanel view;
    JPanel gridPanel;
    JButton[] tileButtons;
    JLabel infoLabel;
    GameController controller;

    public GamePanel(GameController controller) {
        this.controller = controller;

        gridPanel = new JPanel();
        gridPanel.setBackground(Color.LIGHT_GRAY);   // 자리 확인용 색

        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setPreferredSize(new Dimension(150, 0));   // 오른쪽 정보 영역 너비

        view = new JPanel(new BorderLayout());
        view.add(gridPanel, BorderLayout.CENTER);
        view.add(infoLabel, BorderLayout.EAST);
    }

    // MainFrame이 창에 붙일 때 꺼내 가는 메서드
    public JPanel getView() {
        return view;
    }

    // 크기 n을 받아 버튼 n*n개 격자 만들기
    public void buildGrid(int n) {
        // TODO
    }

    // tiles[k] 숫자를 k번 버튼 글자로 (0이면 빈칸)
    public void drawBoard(int[] tiles) {
        // TODO
    }
}
