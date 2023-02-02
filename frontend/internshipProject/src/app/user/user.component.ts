import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})

export class UserComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    
  }

  register(val1: any, val2: any, val3: any, val4: any, val5: any, val6: any){
    this.userService.registerNewUser(val1, val2, val3, val4, val5, val6);
  }

  redirectToLogin() {
    this.router.navigate(['/login']);
  }
}
