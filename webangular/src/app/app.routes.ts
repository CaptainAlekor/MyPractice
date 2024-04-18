import { Routes } from '@angular/router';
import { TestCompComponent } from './test-comp/test-comp.component';
import { TablesListComponent } from './tables-list/tables-list.component';
import { StudentsPageComponent } from './students-page/students-page.component';
import { ProfessorsPageComponent } from './professors-page/professors-page.component';

export const routes: Routes = [
    {path: 'test', component: TestCompComponent},
    {path: '', component: TablesListComponent},
    {path: 'students', component: StudentsPageComponent },
    {path: 'professors', component: ProfessorsPageComponent}
];
