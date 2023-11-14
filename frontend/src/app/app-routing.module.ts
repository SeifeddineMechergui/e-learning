import { RouteGuardService } from './service/route-guard.service';
import { LogoutComponent } from './logout/logout.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';
import { RegisterComponent } from './register/register.component';
import { ListCoursesComponent } from './list-courses/list-courses.component';
import { CourseDataComponent } from './list-courses/course-data/course-data.component';
import { CourseViewEditComponent } from './list-courses/course-view-edit/course-view-edit.component';

// welcome 
const routes: Routes = [
  { path: '', component: LoginComponent  },//canActivate, RouteGuardService
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'welcome', component: WelcomeComponent, canActivate:[RouteGuardService]},
  { path: 'courses', component: ListCoursesComponent, canActivate:[RouteGuardService]},
  { path: 'course-data', component: CourseDataComponent, canActivate:[RouteGuardService]},
  { path: 'courses/:id', component: CourseViewEditComponent, canActivate:[RouteGuardService]},
  { path: 'logout', component: LogoutComponent, canActivate:[RouteGuardService] },

  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
