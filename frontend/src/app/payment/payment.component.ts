import {Component, forwardRef, Inject, OnInit} from '@angular/core';
import {LocalStorageService} from "angular-2-local-storage";
import {User} from "../user";
import {UserService} from "../user.service";
import {AppComponent} from "../app.component";
import {Product} from "../product";
import {Router} from "@angular/router";

@Component({
  selector: 'payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  key = 'cart';
  title = 'Payment | Food EZ';
  currentUser: User;

  constructor(@Inject(forwardRef(() => AppComponent)) private _parent: AppComponent,
              private userService: UserService, private _localStorageService: LocalStorageService,
              private router: Router) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(this._localStorageService.get("current_user"));
    if (this.currentUser == null)
      this.router.navigate(['/']);
  }

  onSubmit() {
    this.userService.purchase(this.currentUser);
    this.currentUser.cart = new Array<Product>();
    this._localStorageService.set("current_user", JSON.stringify(this.currentUser));
    this._parent.refresh();
  }
}
