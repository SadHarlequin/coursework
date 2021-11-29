import {Component, OnInit} from '@angular/core';
import {User} from "./user";
import {UserService} from "./user.service";
import {LocalStorageService} from "angular-2-local-storage";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [
    './app.component.css'
  ]
})
export class AppComponent implements OnInit{
  key = 'menu';
  title = "Welcome | Food EZ";
  compNumber = 0;
  formUser: User = new User();
  currentUser: User = new User();

  constructor(private userService: UserService, private _localStorageService: LocalStorageService) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(this._localStorageService.get("current_user"));
  }

  public change_outlet(event: any) {
    if (event.hasOwnProperty('key') && event.hasOwnProperty('title'))
      switch (event.key) {
        case 'menu':
          this.compNumber = 0;
          break;
        case 'about-us':
          this.compNumber = 1;
          break;
        case 'products':
          this.compNumber = 2;
          break;
        case 'cart':
          this.compNumber = 3;
          break;
        case 'contact-us':
          this.compNumber = 4;
          break;
      }
    this.title = event['title'];
  }

  public onSubmitRegistration() {
    if (this.formUser.password == this.formUser.retypedPassword) {
      this.userService.save(this.formUser);
    }
  }

  public onSubmitLogin() {
    const username_regex = new RegExp("^[A-Za-z0-9_]+$");
    const email_regex = new RegExp("^\\S+@\\S+\\.\\S+$");
    if (username_regex.test(this.formUser.username)) {
      this.userService.getUser(this.formUser.username, null, this.formUser.password).subscribe((current: User) => {
        this.currentUser = current;
        this._localStorageService.set("current_user", JSON.stringify(current));
      });
    }
    if (email_regex.test(this.formUser.username)) {
      this.userService.getUser(null, this.formUser.username, this.formUser.password).subscribe((current: User) => {
        this.currentUser = current;
        this._localStorageService.set("current_user", JSON.stringify(current));
      });
    }
  }

  public logout(){
    this._localStorageService.remove("current_user");
    this.currentUser = null;
  }

  public refresh(){
    this.currentUser = JSON.parse(this._localStorageService.get("current_user"));
  }

}
