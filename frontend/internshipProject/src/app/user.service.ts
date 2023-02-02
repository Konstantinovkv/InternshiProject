import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private registerUserUrl = environment.API_URL + "user/register";

  constructor(private http: HttpClient) { }

  registerNewUser(val1: any, val2: any, val3: any, val4: any, val5: any, val6: any): void {
    var address = {
      country: val4,
      city: val5,
      street: val6
    }
    var user = { 
      username: val1,
      email: val2,
      password: val3,
      address: address
   };
    
    this.http.post(`${this.registerUserUrl}`, user).subscribe((res) => {console.log(res)});
  }
}
