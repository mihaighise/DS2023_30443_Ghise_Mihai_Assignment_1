import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users!: User[];

  constructor(private userSerivce: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  public getUsers() {
    this.userSerivce.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
      }
    )
  }

}
