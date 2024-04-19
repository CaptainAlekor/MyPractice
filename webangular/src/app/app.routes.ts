import { Routes } from '@angular/router';
import { TestCompComponent } from './test-comp/test-comp.component';
import { TablesListComponent } from './tables-list/tables-list.component';
import { StudentsPageComponent } from './students-page/students-page.component';
import { ProfessorsPageComponent } from './professors-page/professors-page.component';
import { GroupsListComponent } from './groups-list/groups-list.component';
import { CoursesPageComponent } from './courses-page/courses-page.component';
import { GroupCoursesPageComponent } from './group-courses-page/group-courses-page.component';
import { ProfessorGroupCoursesPageComponent } from './professor-group-courses-page/professor-group-courses-page.component';

export const routes: Routes = [
    {path: 'test', component: TestCompComponent},
    {path: '', component: TablesListComponent},
    {path: 'students', component: StudentsPageComponent },
    {path: 'professors', component: ProfessorsPageComponent},
    {path: 'groups', component: GroupsListComponent},
    {path: 'courses', component: CoursesPageComponent},
    {path: 'group-courses', component: GroupCoursesPageComponent},
    {path: 'professor-group-courses', component: ProfessorGroupCoursesPageComponent}
];
