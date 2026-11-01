import javax.swing.*;
import java.awt.*;

public class GamePanel {

    private JPanel view;                // 창에 붙는 바깥 상자 (가운데: 격자, 오른쪽: 정보 영역)
    private JPanel gridPanel;           // 타일 버튼 격자
    private JButton[] tileButtons;      // 칸 번호 0 ~ N*N-1 순서
    private JLabel infoLabel;           // 판 크기 표시
    private GameController controller;  // 클릭을 전달

    public GamePanel(GameController controller) {

        this.controller = controller;

        gridPanel = new JPanel();
        gridPanel.setBackground(Color.LIGHT_GRAY);

        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        infoLabel.setPreferredSize(new Dimension(150, 0));   // 오른쪽 정보 영역 너비

        view = new JPanel(new BorderLayout());
        view.add(gridPanel, BorderLayout.CENTER);
        view.add(infoLabel, BorderLayout.EAST);
    }

    // MainFrame이 창에 붙일 때 꺼내 가는 메서드
    public JPanel getView() {
        return view;
    }

    // 크기 size를 받아 버튼 size*size 격자 만들기 + 정보 영역에 판 크기 표시
    public void buildGrid(int size) {

        gridPanel.removeAll();                              // 이전 버튼 비우기
        gridPanel.setLayout(new GridLayout(size, size));    // size*size 격자

        tileButtons = new JButton[size * size];         // 칸 수만큼 새 배열

        for (int cellNo = 0; cellNo < size * size; cellNo++) {  // 칸 번호 순서대로
            JButton button = new JButton();
            button.setFont(new Font("맑은 고딕", Font.BOLD, 24));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);  // 클릭 후 생기는 테두리 없애기

            final int clickedCell = cellNo; // 람다 안에서 쓸 칸 번호 복사본
            button.addActionListener(e -> controller.onTileClick(clickedCell)); // 입력은 전달만

            tileButtons[cellNo] = button;
            gridPanel.add(button);  // 추가한 순서대로 왼쪽 위부터 채워짐
        }

        infoLabel.setText("<html><center>판 크기<br>" + size + " × " + size + "</center></html>");

        gridPanel.revalidate(); // 재배치
        gridPanel.repaint();    // 다시 그리기
    }

    // tiles[k] 숫자를 k번 버튼 글자로 (0이면 빈칸)
    public void drawBoard(int[] tiles) {
        for (int cellNo = 0; cellNo < tileButtons.length; cellNo++) {
            JButton button = tileButtons[cellNo];

            if (tiles[cellNo] == 0) {                         // 빈칸
                button.setText("");
                button.setBackground(Color.LIGHT_GRAY);
            } else {                                          // 숫자 타일
                button.setText(String.valueOf(tiles[cellNo]));
                button.setBackground(Color.WHITE);
            }
        }
    }

    // 팝업: 버튼 글자를 받아 띄우고, 누른 버튼 순서(0, 1, …)를 돌려줌. 팝업 X로 닫으면 -1
    public int showMessage(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(view, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
    }
}