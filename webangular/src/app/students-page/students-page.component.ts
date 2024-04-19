import { Component, NgModule, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { NgIf, NgFor } from '@angular/common';
import axios from 'axios';
import { FormsModule } from '@angular/forms';

class Student {
  public id: number;
  public name: string;
  public surname: string;
  public group: string;

  constructor(id: number, name: string, surname: string, group: string) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.group = group;
  }
}

@Component({
  selector: 'app-students-page',
  standalone: true,
  imports: [MatTableModule, NgIf, NgFor, FormsModule],
  templateUrl: './students-page.component.html',
  styleUrl: './students-page.component.css',
})
export class StudentsPageComponent implements OnInit {
  studentList: Student[] = [];
  isDataLoaded: boolean = false;
  columnsToDisplay = ['id', 'name', 'surname','group'];
  showCreateDialog: boolean = false;
  createStudentForm: any = {
    email: '',
    password: '',
    name: '',
    surname: '',
    groupName: ''
  }

  ngOnInit(): void {
    axios
      .get('http://localhost:8080/students')
      .then((response) => {
        response.data.forEach((elem: any) => {
          this.studentList.push(
            new Student(elem.id, elem.name, elem.surname, elem.group.name)
          );
        });
      })
      .then(() => {
        this.isDataLoaded = true;
      });
  }

  checkForm() {
    axios.post('http://localhost:8080/students/create', {
      email: this.createStudentForm.email,
      password: this.createStudentForm.password,
      name: this.createStudentForm.name,
      surname: this.createStudentForm.surname,
      groupName: this.createStudentForm.groupName
    }).then((response) => {
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
