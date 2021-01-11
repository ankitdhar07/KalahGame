import { Component, OnInit } from '@angular/core';
import { InitializeBoardService } from '../service/data/initialize-board.service';
import { Game } from '../Model/KalahModel';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  Game: any;
  pitIndex: number;
  StoneCountPitOne: String;
  StoneCountPitTwo: String;
  StoneCountPitThree: String;
  StoneCountPitFour: String;
  StoneCountPitFive: String;
  StoneCountPitSix: String;
  StoneCountPitSeven: String;
  StoneCountPitEight: String;
  StoneCountPitNine: String;
  StoneCountPitTen: String;
  StoneCountPitElevan: String;
  StoneCountPitTwelve: String;
  StoneCountPitThirteen: String;
  StoneCountPitFourteen: String;
  constructor(private service: InitializeBoardService) {
  }
  ngOnInit(): void {
  }
  getStoneValue() {
    this.service.executeInitializeBoardService().subscribe(
      response => {
        this.Game = response;
        this.StoneCountPitOne = this.Game.board.pits[1].stoneCount;
        this.StoneCountPitTwo = this.Game.board.pits[2].stoneCount;
        this.StoneCountPitThree = this.Game.board.pits[3].stoneCount;
        this.StoneCountPitFour = this.Game.board.pits[4].stoneCount;
        this.StoneCountPitFive = this.Game.board.pits[5].stoneCount;
        this.StoneCountPitSix = this.Game.board.pits[6].stoneCount;
        this.StoneCountPitSeven = this.Game.board.pits[7].stoneCount;
        this.StoneCountPitEight = this.Game.board.pits[8].stoneCount;
        this.StoneCountPitNine = this.Game.board.pits[9].stoneCount;
        this.StoneCountPitTen = this.Game.board.pits[10].stoneCount;
        this.StoneCountPitElevan = this.Game.board.pits[11].stoneCount;
        this.StoneCountPitTwelve = this.Game.board.pits[12].stoneCount;
        this.StoneCountPitThirteen = this.Game.board.pits[13].stoneCount;
        this.StoneCountPitFourteen = this.Game.board.pits[14].stoneCount;
        console.log(this.Game.board.pits[7].stoneCount)
      });
  }
}
