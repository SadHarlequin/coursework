import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Product} from "../product";
import {User} from "../user";
import {LocalStorageService} from "angular-2-local-storage";
import {ProductService} from "../product-service.service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  title = 'Product Details | Food EZ';
  key = 'products';
  productId: number;
  product: Product;
  currentUser: User;

  constructor(private activatedRoute: ActivatedRoute, private _localStorageService: LocalStorageService, private productService: ProductService) {
    this.activatedRoute.queryParams.subscribe(params => {
      this.productId = params['id'];
    })
  }

  ngOnInit() {
    this.currentUser = JSON.parse(this._localStorageService.get('current_user'));
    this.productService.getProductById(this.productId).subscribe((current: Product) => {
      this.product = current;
    });
  }

}
