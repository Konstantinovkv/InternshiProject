import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})

export class UserComponent implements OnInit {

  private registerUserUrl = environment.API_URL + "user/register";

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    
  }

  register(val1: any, val2: any, val3: any, val4: any, val5: any, val6: any){
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
    
    this.http.post(`${this.registerUserUrl}`, user, {withCredentials: true}).subscribe((res) => {console.log(res)});
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }
}
