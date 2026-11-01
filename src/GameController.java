// 게임 진행 계층: 화면에서 받은 입력으로 Board를 호출하고, 결과에 따라 화면 갱신·팝업을 지시한다.

public class GameController {

    private Board board;        // 판 상태와 규칙
    private GamePanel panel;    // 판 화면
    private int size;           // 현재 판 크기

    // 화면을 만든 뒤 한 번 넣어 준다
    public void setPanel(GamePanel panel) {
        this.panel = panel;
    }

    // 실행 시(3×3), Size 메뉴 선택 시: 선택한 크기로 새 판 + 격자 다시 만들기
    public void startGame(int n) {
        size = n;
        board = new Board(n);                 // 생성자 안에서 섞음
        panel.buildGrid(n);
        panel.drawBoard(board.getTiles());
    }

    // New Game, 완성 후 "계속"버튼 -> 같은 크기로 다시 섞기 (Board 재사용)
    public void onNewGame() {
        board.shuffle();
        panel.drawBoard(board.getTiles());
    }

    // 타일 클릭 : 이동(이웃일 때만) → 다시 그리기 → 완성 판정
    public void onTileClick(int cellNo) {
        board.canMove(cellNo);
        panel.drawBoard(board.getTiles());

        if (board.isSolved()) {
            int choice = panel.showMessage("퍼즐을 완성했습니다!\n같은 크기로 계속하시겠습니까?",
                    "완성", new String[]{"계속", "종료"});
            if (choice == 1)            // 종료
                System.exit(0);
            else                        // 계속 (팝업 X로 닫은 경우도 계속)
                onNewGame();
        }
    }

    // 창 닫기, Exit 메뉴 : 중단 확인 팝업
    public void onCloseRequest() {
        int choice = panel.showMessage("게임을 중단하시겠습니까?", "중단 확인", new String[]{"재개", "중단"});
        if (choice == 1)                // 중단
            System.exit(0);
        // 재개 또는 팝업 X: 아무것도 하지 않음 (판 그대로)
    }
}