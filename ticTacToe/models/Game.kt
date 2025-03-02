package ticTacToe.models

class Game(
    private var players: ArrayDeque<Player>
) {
    private lateinit var playingBoard: Board

    fun init(size: Int) {
        this.playingBoard = Board(size = size)
        if (players.size != 2) {
            throw Error("There should be only 2 players")
        }
        startGame()
    }

    private fun startGame() {
        while(true){

            val playerWithTurn = players.removeFirst()
            println("${playerWithTurn.name} : turn -> please input coordinates comma separated - ")
            printGrid(playingBoard)

            val input = readInputFromConsole()
            val placed = playingBoard.placePiece(input.first, input.second, playerWithTurn.playingPiece.pieceType)
            if(!placed){
                println("That place is either occupied or out of the board pls try again...")
                players.addFirst(playerWithTurn)
                continue
            }
            val emptySpace = playingBoard.emptySpace()
            if(emptySpace.isEmpty()){
                println("Tie game")
                return
            }
            val isWinner = isWinner(playingBoard, input.first, input.second)

            if(isWinner){
                println("Winner of this game is ${playerWithTurn.name}")
                return
            }
            players.addLast(playerWithTurn)
        }
    }
    private fun printGrid(playingBoard: Board) {
        for(row in 0 ..<3){
            for(col in 0 ..<3){
                if(playingBoard.board[row][col] == null){
                    print(" ")
                }else
                    print(playingBoard.board[row][col].toString())
                print("|")
            }
            println()
            println("---------")
        }
    }
    private fun readInputFromConsole(): Pair<Int,Int> {
        val input = readlnOrNull()
        var pair: Pair<Int, Int>? = null
        if (input != null) {
            val coordinates = input.split(",")

            if (coordinates.size == 2) {
                val x = coordinates[0].trim().toInt()
                val y = coordinates[1].trim().toInt()
                pair = Pair(x,y)
                println("Coordinates: x = $x, y = $y")
            } else {
                println("Invalid input! Please enter two comma-separated integers.")
            }
        } else {
            println("No input received.")
        }
        return  pair!!
    }

    private fun isWinner(playingBoard: Board, x: Int, y: Int): Boolean {
        val pieceType = playingBoard.board[x][y]
        var winner = true
        for (col in 0..<3) {
            if (playingBoard.board[x][col] != pieceType) {
                winner = false
                break;
            }
        }
        if (winner) return true
        winner = true
        for (row in 0..<3) {
            if (playingBoard.board[row][y] != pieceType) {
                winner = false
                break;
            }
        }
        if (winner) return true

        winner = true
        for (xy in 0..<3) {
            if (playingBoard.board[xy][xy] != pieceType) {
                winner = false
                break
            }
        }
        if (winner) return true
        winner = true
        for (xy in 0..<3) {
            if (playingBoard.board[2 - xy][xy] != pieceType) {
                winner = false
                break
            }
        }
        return winner

    }
}