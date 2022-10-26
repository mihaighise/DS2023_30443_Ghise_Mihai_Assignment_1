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
  selectedFreeDevices: Device[] = [];

  showAddUser: boolean = false;
  showAddDevice: boolean = false;

  showEditUser: boolean = false;
  showEditDevice: boolean = false;

  roleUser: boolean = false;
  roleAdmin: boolean = false;

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

  deleteUser(username: string) {
    this.userSerivce.deleteUser(username).subscribe();
    window.location.reload();
  }

  toogleClick(device: Device) {
    if (this.checkSelectedDevice(device)) {
      const index = this.selectedFreeDevices.indexOf(device, 0);
      if (index > -1) {
        this.selectedFreeDevices.splice(index, 1);
      }
      console.log(this.selectedFreeDevices);
    } else {
      this.selectedFreeDevices.push(device);
      console.log(this.selectedFreeDevices);
    }
  }

  checkSelectedDevice(device: Device) {
    if (this.selectedFreeDevices.find(x => x === device))
      return true;
    return false;
  }

  assignDevicesToUser(id: number | undefined) {
    if(this.selectedFreeDevices.length > 0) {
      //call service to assign device to user
      console.log(this.selectedFreeDevices);
      this.userSerivce.assignDevicesToUser(id, this.selectedFreeDevices).subscribe();
      window.location.reload();
    }
  }

  checkIfSelectedDevicesNotEmpty(): boolean {
    if(this.selectedFreeDevices.length === 0)
      return false;
    return true;
  }

  toogleAddUser() {
    this.showAddUser = !this.showAddUser;
  }

  toogleAddDevice() {
    this.showAddDevice = !this.showAddDevice;
  }

  toggleEditUser() {
    this.showEditUser = !this.showEditUser;
  }

  toggleEditDevice() {
    this.showEditDevice = !this.showEditDevice;
  }

  chooseUserRole() {
    if(this.roleAdmin === false) {
      this.roleUser = !this.roleUser;
    } else {
      this.roleAdmin = false;
      this.roleUser = !this.roleUser
    }
  }

  chooseAdminRole() {
    if(this.roleUser === false) {
      this.roleAdmin = !this.roleAdmin;
    } else {
      this.roleUser = false;
      this.roleAdmin = !this.roleAdmin;
    }
  }

  addNewUser() {
    let username = (<HTMLInputElement>document.getElementById('username')).value;
    let password = (<HTMLInputElement>document.getElementById('password')).value;
    let userRole = '';

    if(this.roleAdmin === true) {
      userRole = 'ADMIN';
    } else {
      userRole = 'USER';
    }


    let newUser = {
      username: username,
      password: password,
      userRole: userRole
    }

    this.userSerivce.addUser(newUser).subscribe();
  }

  editUser() {
    let oldUsername = document.getElementById('oldUsername')?.textContent;
  
    let username = (<HTMLInputElement>document.getElementById('username')).value;
    let password = (<HTMLInputElement>document.getElementById('password')).value;
    let userRole = '';

    if(this.roleAdmin === true) {
      userRole = 'ADMIN';
    } else {
      userRole = 'USER';
    }

    let newUser = {
      username: username,
      password: password,
      userRole: userRole
    }
    if(oldUsername !== null)
      this.userSerivce.updateUser(oldUsername, newUser).subscribe();
  }

  addNewDevice() {
    let description = (<HTMLInputElement>document.getElementById('description')).value;
    let address = (<HTMLInputElement>document.getElementById('address')).value;
    let maximumEnergy = Number((<HTMLInputElement>document.getElementById('energy')).value);

    let newDevice = {
      description: description,
      address: address,
      maximumEnergy: maximumEnergy
    }

    this.deviceService.addDevice(newDevice).subscribe();
  }
}
