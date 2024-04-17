import { Component } from '@angular/core';
import {MatTableModule} from '@angular/material/table'; 

@Component({
  selector: 'app-students-page',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './students-page.component.html',
  styleUrl: './students-page.component.css'
})
export class StudentsPageComponent {

}
