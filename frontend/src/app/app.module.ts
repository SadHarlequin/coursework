import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './user.service';
import { AboutUsComponent } from './about-us/about-us.component';
import { MenuComponent } from './menu/menu.component';
import { ProductsComponent } from './products/products.component';
import { CartComponent } from './cart/cart.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { PaymentComponent } from './payment/payment.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import {NgbPopoverModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {LocalStorageModule} from "angular-2-local-storage";
import {ProductService} from "./product-service.service";



@NgModule({
  declarations: [
    AppComponent,
    AboutUsComponent,
    MenuComponent,
    ProductsComponent,
    CartComponent,
    ProductDetailsComponent,
    PaymentComponent,
    ContactUsComponent
  ],
  imports: [
    LocalStorageModule.forRoot({
      prefix: "app",
      storageType: "localStorage"
    }),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbPopoverModule,
    NgbModule
  ],
  providers: [UserService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
