import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { NgIf, NgFor } from '@angular/common';
import axios from 'axios';
import { FormsModule } from '@angular/forms';

class GroupCourse {
  public id: number
  public group: string
  public course: string

  constructor(id: number, group: string, course: string) {
    this.id = id;
    this.group = group;
    this.course = course;
  }
}


@Component({
  selector: 'app-group-courses-page',
  standalone: true,
  imports: [MatTableModule, NgIf, NgFor, FormsModule],
  templateUrl: './group-courses-page.component.html',
  styleUrl: './group-courses-page.component.css'
})
export class GroupCoursesPageComponent {
  showCreateDialog: boolean = false
  isDataLoaded: boolean = false
  groupCoursesList: GroupCourse[] = []
  columnsToDisplay = ['id', 'group', 'course']
  createGroupCourseForm: any = {
    groupName: '',
    courseId: ''
  }

  ngOnInit(): void {
    axios
      .get('http://localhost:8080/groupCourses')
      .then((response) => {
        response.data.forEach((elem: any) => {
          this.groupCoursesList.push(
            new GroupCourse(elem.id, elem.group.name, elem.course.name)
          );
        });
      })
      .then(() => {
        this.isDataLoaded = true;
      });
  }

  checkForm() {
    axios.post('http://localhost:8080/groupCourses/create', {
      groupName: this.createGroupCourseForm.groupName,
      courseId: this.createGroupCourseForm.courseId
    })
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
