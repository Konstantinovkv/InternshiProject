import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  private complaintUrl = environment.API_URL + "complaint";

  constructor(private http: HttpClient, private router: Router) { }

  complaint(val1: any, val2: any): void {
    var complaint = {
      text: val1,
      password: val2
    };

    this.http.post(`${this.complaintUrl}`, complaint).subscribe((res) => {console.log(res)});
  }
}
