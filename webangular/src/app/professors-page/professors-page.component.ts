import { Component, NgModule, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { NgIf, NgFor } from '@angular/common';
import axios from 'axios';
import { FormsModule } from '@angular/forms';

class Professor {
  public id: number
  public name: string
  public surname: string

  constructor(id: number, name: string, surname: string) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }
}

@Component({
  selector: 'app-professors-page',
  standalone: true,
  imports: [MatTableModule, NgIf, NgFor, FormsModule],
  templateUrl: './professors-page.component.html',
  styleUrl: './professors-page.component.css'
})
export class ProfessorsPageComponent implements OnInit {
  showCreateDialog: boolean = false
  isDataLoaded: boolean = false
  professorList: Professor[] = []
  columnsToDisplay = ['id', 'name', 'surname']
  createProfessorForm: any = {
    email: '',
    password: '',
    name: '',
    surname: ''
  }


  ngOnInit(): void {
    axios
      .get('http://localhost:8080/professors')
      .then((response) => {
        response.data.forEach((elem: any) => {
          this.professorList.push(
            new Professor(elem.id, elem.name, elem.surname)
          );
        });
      })
      .then(() => {
        this.isDataLoaded = true;
      });
  }

  checkForm() {
    axios.post('http://localhost:8080/professors/create', {
      email: this.createProfessorForm.email,
      password: this.createProfessorForm.password,
      name: this.createProfessorForm.name,
      surname: this.createProfessorForm.surname,
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
