import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Subject} from "rxjs";
import {Product} from "./product";
import {Category} from "./category";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productsUrl: string;
  private categoriesUrl: string;
  private productNamesUrl: string;
  private brandNamesUrl: string;
  private cartUpdateUrl: string;

  constructor(private http: HttpClient) {
    this.productsUrl = 'http://localhost:8080/products';
    this.productNamesUrl = this.productsUrl + "/names";
    this.brandNamesUrl = this.productsUrl + "/brands";
    this.categoriesUrl = "http://localhost:8080/categories";
    this.cartUpdateUrl = "http://localhost:8080/cart/update";
  }

  public updateCart(user: User){
    let c: User;
    var subject = new Subject<User>();
    this.postData(this.cartUpdateUrl, user).subscribe((current: User) => {
      c = current;
      subject.next(c);
    });
    return subject.asObservable();
  }

  public getProducts() {
    let products: Array<Product>;
    var subject = new Subject<Array<Product>>();
    this.getData(this.productsUrl, null).subscribe((current: Array<Product>) => {
      products = current;
      subject.next(products);
    });
    return subject.asObservable();
  }

  public getCategories() {
    let categories: Array<Category>;
    var subject = new Subject<Array<Category>>();
    this.getData(this.categoriesUrl, null).subscribe((current: Array<Category>) => {
      categories = current;
      subject.next(categories);
    });
    return subject.asObservable();
  }

  public getProductNames(){
    let categories: Array<String>;
    var subject = new Subject<Array<String>>();
    this.getData(this.productNamesUrl, null).subscribe((current: Array<String>) => {
      categories = current;
      subject.next(categories);
    });
    return subject.asObservable();
  }

  public getBrandNames(){
    let brands: Array<String>;
    var subject = new Subject<Array<String>>();
    this.getData(this.brandNamesUrl, null).subscribe((current: Array<String>) => {
      brands = current;
      subject.next(brands);
    });
    return subject.asObservable();
  }

  public getProductById(id){
    let product: Product;
    var subject = new Subject<Product>();
    this.getData(this.productsUrl + "/" + id, null).subscribe((current: Product) => {
      product = current;
      subject.next(product);
    });
    return subject.asObservable();
  }

  public getData(url, params: HttpParams) {
    return this.http.get(url, {params});
  }

  public postData(url, body) {
    return this.http.post(url, body);
  }
}
