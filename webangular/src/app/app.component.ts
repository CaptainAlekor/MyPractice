import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TestCompComponent } from './test-comp/test-comp.component';
import { TablesListComponent } from './tables-list/tables-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TestCompComponent, TablesListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  
}
