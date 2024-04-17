import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

class User {
  public name: string = 'kek'
  public val: number = 1
}

@Component({
  selector: 'app-test-comp',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './test-comp.component.html',
  styleUrl: './test-comp.component.css'
})
export class TestCompComponent {
  bebra: string = 'bebra'
  arr: number[] = [1, 2, 3, 6]

  userList: User[] = new Array(new User(), {name: 'sas', val: 2})
}