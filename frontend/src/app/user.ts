import {Product} from "./product";

export class User {
  id: number;
  username: string;
  email: string;
  password: string;
  retypedPassword: string;
  cart: Array<Product> = new Array<Product>();

  constructor() {
  }
}
