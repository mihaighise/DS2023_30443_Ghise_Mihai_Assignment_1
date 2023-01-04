import { Component } from '@angular/core';
import { ChatServiceClient } from '../../proto/chatapp.client';
import type { ChatMessage, CustomUser } from '../../proto/chatapp';
import {GrpcWebFetchTransport} from "@protobuf-ts/grpcweb-transport"

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  receiver: String = '';


  async sendMessage() {
    let message = (<HTMLInputElement>document.getElementById("input_message")).value;
    console.log(message);
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:8080"
    });
    let client = new ChatServiceClient(transport);
    let { response } = await client.sendMsg({ from: "A", to: "B", msg: message } as ChatMessage);
    console.log("got a small hat! " + response)
  }

  chatWith(user: string) {
    this.receiver = user
  }

}
