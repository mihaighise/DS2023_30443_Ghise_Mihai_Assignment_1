import { Device } from "./device";

export interface User {
    id: number;
    username: string;
    password: string;
    userRole: string;
    devices: Device[]
}