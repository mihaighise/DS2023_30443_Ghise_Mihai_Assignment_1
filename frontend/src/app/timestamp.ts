import { Device } from "./device";

export interface Timestamp {
    id?: number,
    device: Device,
    time: Date
    consumption: number;
}