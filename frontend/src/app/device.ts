import { User } from "./user";

export interface Device {
    id: number;
    description: string;
    address: string;
    maximumEnergy: number;
    user: User;
}