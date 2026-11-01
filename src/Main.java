import javax.swing.*;

void main() {

    Board b3 = new Board(3);
    b3.boardPrint();

    b3.shuffle();

    b3.boardPrint();

    GameController controller = new GameController();
    SwingUtilities.invokeLater(() -> new MainFrame(controller));
}
