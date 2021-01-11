import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class InitializeBoardService {
  pitIndex: number;
  Game: any;
  constructor(
    private http: HttpClient,
  ) {
  }
  executeInitializeBoardService() {
    return this.http.get(`http://localhost:8080/kalah/game`);
  }

  excuteGamePlay(pitIndex) {
    console.log("executed");
    return this.http.put(`http://localhost:8080/kalah/game/pits/${pitIndex}`, this.pitIndex);
    ;
  }
}
