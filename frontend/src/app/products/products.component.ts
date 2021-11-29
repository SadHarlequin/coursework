import {Component, OnInit, Output} from '@angular/core';
import { Inject, forwardRef } from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product-service.service";
import {Category} from "../category";
import {User} from "../user";
import {LocalStorageService} from "angular-2-local-storage";
import {EventEmitter} from "@angular/core";
import {AppComponent} from "../app.component";

@Component({
  selector: 'products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  key = 'products';
  title = 'Products | Food EZ';

  products: Array<Product>;
  productNames: Array<String>;
  brandNames: Array<String>;
  filteredProducts: Array<Product>;
  categories: Array<Category>;
  index = 1;

  currentUser: User;

  filterProductName: string = null;
  filterCategory: string = null;
  filterBrand: string = null;
  filterPrice: string = null;

  constructor(@Inject(forwardRef(() => AppComponent)) private _parent: AppComponent, private productService: ProductService, private _localStorageService: LocalStorageService) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(this._localStorageService.get('current_user'));
    this.productService.getProducts().subscribe((current: Array<Product>) => {
      this.products = current;
      this.filteredProducts = current;
    });
    this.productService.getCategories().subscribe((current: Array<Category>) => {
      this.categories = current;
    });
    this.productService.getProductNames().subscribe((current: Array<String>) => {
      this.productNames = current;
    });
    this.productService.getBrandNames().subscribe((current: Array<String>) => {
      this.brandNames = current;
    });
  }

  filter(productName: string, category: string, brand: string, price: string) {
    this.filteredProducts = this.products
      .filter(product => productName != "null" && productName ? product.productName == productName : true)
      .filter(product => category != "null" && category? product.category.categoryName == category : true)
      .filter(product => brand != "null" && brand ? product.brandName == brand : true)
      .filter(product => {
        if (price == "null" || !price) return true;
        switch (price) {
          case '0':
            return product.price <= 20;
          case '1':
            return product.price >= 15 && product.price <= 60;
          case '2':
            return product.price >= 50 && product.price <= 200;
        }
      });
  }

  public decreaseIndex() {
    if (this.index - 1 > 0) {
      this.index -= 1;
    }
  }

  public increaseIndex() {
    if (this.index + 1 < 5) {
      this.index += 1;
    }
  }

  public addToCart(product){
    let found = false;
    product.userIds.push(this.currentUser.id);
    this.currentUser.cart.forEach((element) => {
      if (!found && product.fullName == element.fullName){
        element.quantity += 1;
        found = true;
      }
    });
    if (!found) this.currentUser.cart.push(product);

    this.productService.updateCart(this.currentUser).subscribe(() => {
      this._parent.refresh();
    });

    this._localStorageService.set("current_user", JSON.stringify(this.currentUser));
  }
}
