import { Component, Input, OnInit } from '@angular/core';
import { Device } from '../device';
import { DeviceService } from '../device.service';
import { LoginService } from '../login.service';
import { User } from '../user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  devices!: Device[];

  constructor(private deviceService: DeviceService,
              private loginService: LoginService) { }

  ngOnInit(): void {
    this.getDevices(this.loginService.getLoggedUser().username);
  }

  public getDevices(username: string) {
    this.deviceService.getDevicesByUser(username).subscribe(
      (response: Device[]) => {
        this.devices = response;
      }
    )
  }


}
