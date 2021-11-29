import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from './user';
import {Subject} from "rxjs";

@Injectable()
export class UserService {

  private usersUrl: string;
  private paymentUrl: string;


  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
    this.paymentUrl = 'http://localhost:8080/payment/user';
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user).subscribe(() => console.log('send register request'));
  }

  public purchase(user: User){
    return this.http.post<User>(this.paymentUrl, user).subscribe(() => console.log('send purchase request (user)'));
  }

  public getUser(username: string, email: string, password: string) {
    const params = new HttpParams()
      .set('username', username)
      .set('email', email)
      .set('password', password);
    let user: User;
    var subject = new Subject<User>();
    this.getData(this.usersUrl, params).subscribe((current: User) => {
      user = current;
      subject.next(user);
    });
    return subject.asObservable();
  }

  getData(url, params: HttpParams) {
    return this.http.get(url, {params});
  }
}
