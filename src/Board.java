//주요 필드
//tiles, blankPos, neighbors

//주요 메서드
//resetSorted(), canMove(cellNo), move(cellNo), isSolved()

public class Board {
    //판 크기
    private int boardSize;
    // 칸 개수
    private int boardLength;

    // 칸 번호 순서대로 타일을 숫자를 저장한다. 빈칸은 0으로 저장한다.
    // N*N길이 int 1차원 배열 -> 1~n-1, 0 저장
    private int [] tiles;

    // 현재 빈칸의 칸 번호
    // 저장 1~n 범위 값 저장, 0은 의미 없는 값
    private int blankPos;
    // 칸을 정점, 접하는 두 칸을 간선으로 보는 무방향 그래프를 저장한다.
    // 칸 번호마다, 빈칸이 그 칸에 있을 때 옮길 수 있는 이웃 칸 번호를 저장하고, 남는 자리는 0으로 채운다.
    private int [][] neighbors;

    //생성자
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.boardLength = boardSize*boardSize;
        // 정렬된 상태인 1~n-1, 0을 순서대로 저장
        this.tiles = new int[boardLength];
        // resetSorted가 blankPos 초기화를 포함하고 있음
        // 초기 빈간 위치는 마지막 칸임
        this.resetSorted();


        // 이동가능한 칸 2차원 배열에 무방향 그래프로 저장
        this.neighbors = new int[boardLength][4];
        // 1단계: 상하좌우 후보 계산
        for(int row=0;row<boardSize;row++) {
            for(int col=0;col<boardSize;col++) {
                int index = row*boardSize+col;
                neighbors[index][0] = index + 1 - boardSize;
                neighbors[index][1] = index + 1 + boardSize;
                neighbors[index][2] = index + 1 - 1;
                neighbors[index][3] = index + 1 + 1;

            }
        }
        //2단계: 판 밖 후보를 0으로
        for (int index = 0; index < boardSize; index++) {
            //순서대로 상, 하, 좌, 우
            neighbors[index][0] =  0;
            neighbors[boardLength-1-index][1] =  0;
            neighbors[boardSize*index][2] =  0;
            neighbors[boardSize*index+boardSize-1][3] =  0;

        }

    }

    public void resetSorted(){
        for(int index=0;index<boardLength;index++)
            tiles[index] = index+1;
        tiles[boardLength-1] = 0;
        blankPos = boardLength;
    }
    public boolean canMove(int cellNo) {
        return false;
    }
    public void move(int cellNo) {

    }
    //최종 상태인지 판별
    public boolean isSolved() {

        //숫자 타일들이 정렬 되어있으면 자동으로 마지막칸은 빈칸임 -> 반복문 범위 : boardLength-1
        for(int index=0;index<boardLength-1;index++) {
            if(tiles[index] != index+1)
                return false;
        }
        return true;
    }

    // ===== [확인용] 판 상태 출력 — 제출 전 삭제 =====
    public void debugPrint(boolean showNeighbors) {
        System.out.println("========== " + boardSize + "x" + boardSize + " ==========");

        // 1) tiles: 판 모양으로 출력 (빈칸은 _)
        System.out.println("[tiles]");
        for (int i = 0; i < boardLength; i++) {
            System.out.print(tiles[i] == 0 ? "  _" : String.format("%3d", tiles[i]));
            if ((i + 1) % boardSize == 0) System.out.println();
        }

        // 2) blankPos 와 실제 0의 위치 비교
        int zeroAt = -1;
        for (int i = 0; i < boardLength; i++) if (tiles[i] == 0) zeroAt = i + 1;
        System.out.println("[blankPos] " + blankPos + " (tiles에서 0인 칸: " + zeroAt + ") "
                + (blankPos == zeroAt ? "OK" : "불일치!"));

        // 3) neighbors: 칸마다 상 하 좌 우
        if (showNeighbors) {
            System.out.println("[neighbors] 칸: 상 하 좌 우");
            for (int i = 0; i < boardLength; i++) {
                System.out.printf("  %2d: %2d %2d %2d %2d%n",
                        i + 1, neighbors[i][0], neighbors[i][1], neighbors[i][2], neighbors[i][3]);
            }
        }

        // 4) 검사: 이웃 개수 = 4N(N-1), 범위 1~N², 양방향
        int count = 0;
        boolean inRange = true, symmetric = true;
        for (int i = 0; i < boardLength; i++) {
            for (int v : neighbors[i]) {
                if (v == 0) continue;
                count++;
                if (v < 1 || v > boardLength) { inRange = false; continue; }
                boolean back = false;
                for (int w : neighbors[v - 1]) if (w == i + 1) back = true;
                if (!back) symmetric = false;
            }
        }
        int expected = 4 * boardSize * (boardSize - 1);
        System.out.println("[검사] 이웃 개수 " + count + "/" + expected + (count == expected ? " OK" : " FAIL")
                + " | 범위 " + (inRange ? "OK" : "FAIL")
                + " | 양방향 " + (symmetric ? "OK" : "FAIL"));
        System.out.println();
    }

}
