import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../user';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  inputUsername: string = '';
  inputPassword: string = '';
  

  constructor(private loginService: LoginService,
              private router: Router) { }

  ngOnInit(): void {
  }

  logIn() {
    this.inputUsername = (<HTMLInputElement>document.getElementById('username')).value;
    this.inputPassword = (<HTMLInputElement>document.getElementById('password')).value;
    this.loginService.logIn(this.inputUsername, this.inputPassword).subscribe(
      (response: User) => {
        localStorage.setItem('user', response.userRole);
        if(response != null) {
          this.loginService.setLoggedUser(response);
          console.log(this.loginService.getLoggedUser());

          var socket = new SockJS('http://localhost:8080/gs-guide-websocket');
          var stompClient = Stomp.over(socket);
          stompClient.connect({}, function (frame:any) {
              console.log('Connected: ' + frame);
              stompClient.subscribe('/user/' + response.id + '/notification', function (greeting) {
                  alert(JSON.parse(greeting.body).message);
              });
          });

          if(response.userRole === "USER")
              this.router.navigateByUrl("/user");
          else if(response.userRole === "ADMIN")
              this.router.navigateByUrl("/admin");
        }
      }
    )
  }



}
