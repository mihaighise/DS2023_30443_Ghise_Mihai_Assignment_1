import { Component } from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  receiver: String = '';


  sendMessage() {
    let message = (<HTMLInputElement>document.getElementById("input_message")).value;
    console.log(message);
  }

  chatWith(user: string) {
    this.receiver = user
  }

}
