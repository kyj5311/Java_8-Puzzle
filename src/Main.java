import javax.swing.*;

void main() {
    GameController controller = new GameController(); //컨트롤러
    SwingUtilities.invokeLater(() -> {
        new MainFrame(controller);  //화면 생성 + 연결
        controller.startGame(3);    // 실행하면 기본 3×3
    });
}