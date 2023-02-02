import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpStatusCode } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = environment.API_URL + "user/login";

  constructor(private http: HttpClient, private router: Router) { }

  login(val1: any, val2: any): void {
    var login = {
      email: val1,
      password: val2
    };

    console.log("before")
    this.http.post<any>(`${this.loginUrl}`, login, { observe: 'response' }).subscribe(
      (res: HttpResponse<any>) => {
        if (res.status === HttpStatusCode.Unauthorized) {
          this.router.navigate(['/login']);
        } else {
          this.router.navigate(['/complaint']);
        }
      }
    );
  }
}
