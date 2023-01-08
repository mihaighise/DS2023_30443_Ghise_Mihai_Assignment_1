import { Component, OnInit } from '@angular/core';
import { ChatServiceClient } from '../../proto/chatapp.client';
import type { ChatMessage, CustomUser } from '../../proto/chatapp';
import {GrpcWebFetchTransport} from "@protobuf-ts/grpcweb-transport"
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  constructor(private userService: UserService) {}

  receiver: String = '';

  sentMessages: string[] = [];
  receivedMessages: string[] = [];
  users: User[] = [];
  displayIsTyping: boolean = false;
  displaySeenMessages: boolean = false;
  loggedUser: string | null = '';

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.loggedUser = localStorage.getItem('username');
    this.receiveMessage();
    this.userService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        console.log(this.users)
      }
    )
  }


  async sendMessage() {
    let message = (<HTMLInputElement>document.getElementById("input_message")).value;
    console.log(message);
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:10100"
    });
    let client = new ChatServiceClient(transport);
    let { response } = await client.sendMsg({ from: this.loggedUser, to: this.receiver, msg: message } as ChatMessage);
    this.sentMessages.push(message);
    console.log("got a small hat! " + response)
  }

  async chatWith(user: string) {
    //TODO send ACK message to the selected user to show him that you read his messages
    this.receiver = user
    console.log("cc");
    //TODO send a message to other user to let him know that i'm seen his messages
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:10100"
    });
    let client = new ChatServiceClient(transport);
    let { response } = await client.sendMsg({ from: this.loggedUser, to: this.receiver, msg: "generic_message_seen" } as ChatMessage);
    console.log("got a small hat! " + response)
  }

  async receiveMessage() {
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:10100"
    });
    let client = new ChatServiceClient(transport);
    let call = client.receiveMsg({ username: this.loggedUser} as CustomUser)
    for await (let hat of call.responses) {
      console.log(hat)
      if(hat.msg !== 'generic_message_is_typing' && hat.msg !== 'generic_message_is_not_typing' && hat.msg !== 'generic_message_seen') {
        //received normal message
        if(hat.from == this.receiver) {
          this.receivedMessages.push(hat.msg);
        }
        } else if(hat.msg == 'generic_message_is_typing' && hat.from == this.receiver) {
          this.displayIsTyping = true;
        } else if(hat.msg == 'generic_message_is_not_typing' && hat.from == this.receiver) {
          this.displayIsTyping = false;
        } else if(hat.msg == 'generic_message_seen' && hat.from == this.receiver) {
          this.displaySeenMessages = true;
        }
    }
  }

  async focusFunction() {
    console.log("aa");
    //TODO send a message to other user to let him know that i'm typing
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:10100"
    });
    let client = new ChatServiceClient(transport);
    let { response } = await client.sendMsg({ from: this.loggedUser, to: this.receiver, msg: "generic_message_is_typing" } as ChatMessage);
    console.log("got a small hat! " + response)
  }

  async focusOutFunction() {
    console.log("bb");
    //TODO send a message to other user to let him know that i'm no longer typing
    let transport = new GrpcWebFetchTransport({
      baseUrl: "http://localhost:10100"
    });
    let client = new ChatServiceClient(transport);
    let { response } = await client.sendMsg({ from: this.loggedUser, to: this.receiver, msg: "generic_message_is_not_typing" } as ChatMessage);
    console.log("got a small hat! " + response)
  }

}
