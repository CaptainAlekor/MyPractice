import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { NgIf, NgFor } from '@angular/common';
import axios from 'axios';
import { FormsModule } from '@angular/forms';

class Group {
  public id: number
  public name: string

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

@Component({
  selector: 'app-groups-list',
  standalone: true,
  imports: [MatTableModule, NgIf, NgFor, FormsModule],
  templateUrl: './groups-list.component.html',
  styleUrl: './groups-list.component.css'
})
export class GroupsListComponent implements OnInit {
  showCreateDialog: boolean = false
  isDataLoaded: boolean = false
  groupsList: Group[] = []
  columnsToDisplay = ['id', 'name']
  createGroupForm: any = {
    name: ''
  }

  ngOnInit(): void {
    axios
      .get('http://localhost:8080/groups')
      .then((response) => {
        response.data.forEach((elem: any) => {
          this.groupsList.push(
            new Group(elem.id, elem.name)
          );
        });
      })
      .then(() => {
        this.isDataLoaded = true;
      });
  }

  checkForm() {
    axios.post('http://localhost:8080/groups/create', this.createGroupForm.name)
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
