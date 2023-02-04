import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Complaint } from '../complaint';
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

  complaints$!: Observable<Complaint[]>;

  ngOnInit(): void {
    
  }

  registerComplaint(val1: any, val2: any){
    var emailSession = localStorage.getItem("email");

    var complaint = {
      topic: val1,
      text: val2,
      email: emailSession
    };

    this.http.post(`${this.complaintUrl}`, complaint, {withCredentials: true}).subscribe((res) => {console.log(res)});
  }

  getComplaints(){
    let getComplaintsUrl = environment.API_URL + "complaint/" + localStorage.getItem("email");

    this.complaints$ = this.http.get<Complaint[]>(`${getComplaintsUrl}`)
  }

  redirectToComplaint() {
    this.router.navigate(['']);
  }
}
