import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent implements OnInit {
  key = 'contact-us';
  title = 'Contact us | Food EZ';

  constructor() { }

  ngOnInit() {
  }

}
