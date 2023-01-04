import { Component, OnInit } from '@angular/core';
import { ChatServiceClient } from '../../proto/chatapp.client';
import type { ChatMessage, CustomUser } from '../../proto/chatapp';
import {GrpcWebFetchTransport} from "@protobuf-ts/grpcweb-transport"

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  receiver: String = '';

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.receiveMessage();
  }


  async sendMessage() {
    let message = (<HTMLInputElement>document.getElementById("input_message")).value;
    console.log(message);
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:8080"
    });
    let client = new ChatServiceClient(transport);
    let { response } = await client.sendMsg({ from: "B", to: "A", msg: message } as ChatMessage);
    console.log("got a small hat! " + response)
  }

  chatWith(user: string) {
    this.receiver = user
  }

  async receiveMessage() {
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:8080"
    });
    let client = new ChatServiceClient(transport);
    let call = client.receiveMsg({ username: "A"} as CustomUser)
    for await (let hat of call.responses) {
    console.log(hat)
    }
  }

}
