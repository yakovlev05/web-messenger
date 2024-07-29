import {UserModel} from "../user/UserModel.ts";

export type MessageModel = {
    id: number;
    message: string;
    published: Date;
    sender: UserModel
}