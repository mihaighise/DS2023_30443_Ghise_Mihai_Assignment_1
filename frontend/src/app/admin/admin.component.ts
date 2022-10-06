import { Component, OnInit } from '@angular/core';
import { Device } from '../device';
import { DeviceService } from '../device.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users!: User[];
  devices!: Device[];
  freeDevices!: Device[];
  selectedFreeDevices: number[] = [];

  constructor(private userSerivce: UserService,
    private deviceService: DeviceService) { }

  ngOnInit(): void {
    this.getUsers();
    this.getDevices();
    this.getFreeDevices();
  }

  public getUsers() {
    this.userSerivce.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
      }
    )
  }

  public getDevices() {
    this.deviceService.getDevices().subscribe(
      (response: Device[]) => {
        this.devices = response;
      }
    )
  }

  public getFreeDevices() {
    this.deviceService.getFreeDevices().subscribe(
      (response: Device[]) => {
        this.freeDevices = response;
      }
    )
  }

  deleteDevice(id: number) {
    this.deviceService.deleteDevice(id).subscribe();
    window.location.reload();
  }

  toogleClick(id: number) {
    if (this.checkSelectedDevice(id)) {
      const index = this.selectedFreeDevices.indexOf(id, 0);
      if (index > -1) {
        this.selectedFreeDevices.splice(index, 1);
      }
      console.log(this.selectedFreeDevices);
    } else {
      this.selectedFreeDevices.push(id);
      console.log(this.selectedFreeDevices);
    }
  }

  checkSelectedDevice(id: number) {
    if (this.selectedFreeDevices.find(x => x === id))
      return true;
    return false;
  }

}
