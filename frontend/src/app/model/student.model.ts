import { Course } from "./course.model";

export class Student {
    public id : number;
    public name: string;
    public username: string;
    public password: string;
    public email: string;
    public birthdate : string;
    public gender : string;
    public courses: Course[];
}
