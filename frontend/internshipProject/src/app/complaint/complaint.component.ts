import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})

export class ComplaintComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }
  
  private complaintUrl = environment.API_URL + "complaint/register";

  ngOnInit(): void {
    
  }

  registerComplaint(val1: any, val2: any){
    var emailSession = localStorage.getItem("email");

    var complaint = {
      text: val1,
      topic: val2,
      email: emailSession
    };

    this.http.post(`${this.complaintUrl}`, complaint, {withCredentials: true}).subscribe((res) => {console.log(res)});
  }

  redirectToComplaint() {
    this.router.navigate(['']);
  }
}
