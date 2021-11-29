import {Category} from "./category";
import {User} from "./user";

export class Product {
  id: number;
  fullName: string;
  brandName: string;
  productName: string;
  price: number;
  amount: number;
  category: Category;
  quantity: number;
  url: string;
  userIds: Array<number>;


  constructor(id: number, fullName: string, brandName: string, productName: string, price: number, amount: number, category: Category, quantity: number, url: string) {
    this.id = id;
    this.fullName = fullName;
    this.brandName = brandName;
    this.productName = productName;
    this.price = price;
    this.amount = amount;
    this.category = category;
    this.quantity = quantity;
    this.url = url;
    this.userIds = new Array<number>();
  }
}
