import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  private loginUrl = environment.API_URL + "user/login";

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    
  }

  login(val1: any, val2: any){
    var login = {
      email: val1,
      password: val2
    };

    localStorage.setItem("email", val1);

    this.http.post(`${this.loginUrl}`, login, { withCredentials: true }).subscribe((res) => {console.log(res)});
    
  }

  redirectToComplaint() {
    this.router.navigate(['/complaint']);
  }
}
