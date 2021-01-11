export interface Game {
    board: Board;
    playerOne: Player;
    playerTwo: Player;
    winner: null;
    status: string;
}

export interface Board {
    pits: { [key: string]: Pit };
    playerOnePitStoneCount: number;
    playerTwoPitStoneCount: number;
}

export interface Pit {
    pitIndex: number;
    stoneCount: number;
    playerIndex: number;
    playerOneHouse: boolean;
    playerTwoHouse: boolean;
    house: boolean;
    oppositePitIndex: number;
}

export interface Player {
    playerIndex: number;
    playerName: string;
}
