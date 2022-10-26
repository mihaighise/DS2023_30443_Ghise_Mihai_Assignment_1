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
  dt!: Date;
  parsedDate: string = '';

  constructor(private deviceService: DeviceService,
              private loginService: LoginService) { }

  ngOnInit(): void {
    this.getDevices(localStorage.getItem('username'));
  }

  public getDevices(username: string | null) {
    this.deviceService.getDevicesByUser(username).subscribe(
      (response: Device[]) => {
        this.devices = response;
      }
    )
  }

  parseDate() {
    let newDate = new Date(this.dt);
		let stringDate = newDate.toLocaleDateString();
		this.parsedDate = stringDate.split('/')[2] + " " + stringDate.split('/')[0] + " " + stringDate.split('/')[1];
		console.log(this.parsedDate);
		console.log(new Date(new Date(this.parsedDate).getTime() + 24 * 60 * 60 * 1000))
  }


}
