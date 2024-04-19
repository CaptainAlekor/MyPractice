import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { NgIf, NgFor } from '@angular/common';
import axios from 'axios';
import { FormsModule } from '@angular/forms';

class ProfessorGroupCourse {
  public professorId: number;
  public professor: string;
  public group: string;
  public course: string;

  constructor(
    professorId: number,
    professor: string,
    group: string,
    course: string
  ) {
    this.professorId = professorId;
    this.professor = professor;
    this.group = group;
    this.course = course;
  }
}

@Component({
  selector: 'app-professor-group-courses-page',
  standalone: true,
  imports: [MatTableModule, NgIf, NgFor, FormsModule],
  templateUrl: './professor-group-courses-page.component.html',
  styleUrl: './professor-group-courses-page.component.css',
})
export class ProfessorGroupCoursesPageComponent {
  showCreateDialog: boolean = false;
  isDataLoaded: boolean = false;
  professorGroupCoursesList: ProfessorGroupCourse[] = [];
  columnsToDisplay = ['professorId', 'professor', 'group', 'course']
  createProfessorGroupCourseForm: any = {
    professorId: '',
    groupCourseId: ''
  }

  ngOnInit(): void {
    axios
      .get('http://localhost:8080/professors')
      .then((response) => {
        response.data.forEach((professor: any) => {
          professor.groupCourses.forEach((groupCourse: any) => {
            this.professorGroupCoursesList.push(
              new ProfessorGroupCourse(
                professor.id,
                professor.name + ' ' + professor.surname,
                groupCourse.group.name,
                groupCourse.course.name
              )
            );
          });
        });
      })
      .then(() => {
        this.isDataLoaded = true;
      });
  }

  checkForm() {
    axios.put('http://localhost:8080/professors/setGroupCourse', {
      professorId: this.createProfessorGroupCourseForm.professorId,
      groupCourseId: this.createProfessorGroupCourseForm.groupCourseId
    })
    .then((response) => {
      alert(response.data)
      location.reload()
    })
  }

  openDialog() {
    this.showCreateDialog = true;
  }

  closeDialog() {
    this.showCreateDialog = false;
  }
}
