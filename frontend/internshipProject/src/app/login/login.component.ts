import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
    
  }

  login(val1: any, val2: any){
    this.loginService.login(val1, val2);
  }

  redirectToComplaint() {
    this.router.navigate(['/complaint']);
  }
}
