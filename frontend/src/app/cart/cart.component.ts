import {Component, forwardRef, Inject, OnInit} from '@angular/core';
import {LocalStorageService} from "angular-2-local-storage";
import {User} from "../user";
import {ProductService} from "../product-service.service";
import {AppComponent} from "../app.component";
import {Product} from "../product";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  key = 'cart';
  title = 'Cart | Food EZ';
  currentUser: User;
  outOfStock: Array<Product> = new Array<Product>();

  constructor(@Inject(forwardRef(() => AppComponent)) private _parent: AppComponent, private productService: ProductService, private _localStorageService: LocalStorageService) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(this._localStorageService.get("current_user"));
    this.checkCartCorrect();
  }

  public remove(product) {
    this.currentUser.cart.forEach((element, index) => {
      if (element.fullName == product.fullName) this.currentUser.cart.splice(index, 1);
    });
    this.productService.updateCart(this.currentUser).subscribe(() => {
      this.checkCartCorrect();
      this._parent.refresh();
    });
    this._localStorageService.set("current_user", JSON.stringify(this.currentUser));
  }

  public getTotalPrice(){
    let total = 0;
    this.currentUser?.cart.forEach((product) => {
      total += product.price * product.quantity;
    });
    return Math.round(total * 100) / 100;
  }

  public getSubTotalPrice(product){
    let price = product.quantity * product.price;
    return Math.round(price * 100) / 100;
  }

  public increaseQuantity(product){
    this.currentUser.cart.forEach((element) => {
      if (product.fullName == element.fullName){
        element.quantity += 1;
      }
    });
    this.checkCartCorrect();
    if (this.isCorrect()){
      this.productService.updateCart(this.currentUser);
      this._localStorageService.set("current_user", JSON.stringify(this.currentUser));
    }
  }

  public decreaseQuantity(product){
    this.currentUser.cart.forEach((element) => {
      if (product.fullName == element.fullName){
        if (element.quantity - 1 > 0)
          element.quantity -= 1;
        else this.remove(element);
      }
    });
    this.checkCartCorrect();
    this.productService.updateCart(this.currentUser);
    this._localStorageService.set("current_user", JSON.stringify(this.currentUser));
  }

  public checkCartCorrect(){
    this.outOfStock = new Array<Product>();
    this.productService.getProducts().subscribe((current: Array<Product>) => {
      this.currentUser.cart.forEach(userProduct => {
        let freshProduct = this.getProductById(current, userProduct.id);
        freshProduct.quantity = userProduct.quantity;
        if (freshProduct.amount - userProduct.quantity < 0)
          this.outOfStock.push(freshProduct);
      })
    });
  }

  public isCorrect(): boolean{
    return this.outOfStock.length == 0;
  }

  public getProductById(cart, id): Product{
    let found : Product = null;
    cart.forEach(product => {
      if (product.id == id)
        found = product;
    });
    return found;
  }
}
