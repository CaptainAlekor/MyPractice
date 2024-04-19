import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { NgIf, NgFor } from '@angular/common';
import axios from 'axios';
import { FormsModule } from '@angular/forms';

class Course {
  public id: number
  public name: string

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

@Component({
  selector: 'app-courses-page',
  standalone: true,
  imports: [MatTableModule, NgIf, NgFor, FormsModule],
  templateUrl: './courses-page.component.html',
  styleUrl: './courses-page.component.css'
})
export class CoursesPageComponent {
  showCreateDialog: boolean = false
  isDataLoaded: boolean = false
  coursesList: Course[] = []
  columnsToDisplay = ['id', 'name']
  createCourseForm: any = {
    name: ''
  }

  ngOnInit(): void {
    axios
      .get('http://localhost:8080/courses')
      .then((response) => {
        response.data.forEach((elem: any) => {
          this.coursesList.push(
            new Course(elem.id, elem.name)
          );
        });
      })
      .then(() => {
        this.isDataLoaded = true;
      });
  }

  checkForm() {
    axios.post('http://localhost:8080/courses/create', this.createCourseForm.name)
    .then((response) => {
      alert(response.data)
      location.reload()
    })
  }

  openDialog() {
    this.showCreateDialog = true
  }

  closeDialog() {
    this.showCreateDialog = false
  }
}
