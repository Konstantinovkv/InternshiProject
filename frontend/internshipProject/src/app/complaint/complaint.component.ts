import { Component, OnInit } from '@angular/core';
import { ComplaintService } from '../complaint.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})

export class ComplaintComponent implements OnInit {

  constructor(private complaintService: ComplaintService, private router: Router) { }

  ngOnInit(): void {
    
  }

  login(val1: any, val2: any){
    this.complaintService.complaint(val1, val2);
  }

  redirectToComplaint() {
    this.router.navigate(['']);
  }
}
