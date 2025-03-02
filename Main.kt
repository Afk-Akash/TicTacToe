import ticTacToe.models.*

fun main() {
    println("Please input players name comma separated")
    val input = readlnOrNull()
    val deque: ArrayDeque<Player> = ArrayDeque()
    val player1: Player
    val player2: Player

    if (input != null) {
        val names = input.split(",")
        player1 = Player(name = names[0], playingPiece = PlayingPiece(PieceType.X))
        player2 = Player(name = names[1], playingPiece = PlayingPiece(PieceType.O))

        deque.addFirst(player1)
        deque.addFirst(player2)
    }

    val game = Game(deque)

    game.init(3)

}
