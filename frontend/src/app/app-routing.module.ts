import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './admin.guard';
import { AdminComponent } from './admin/admin.component';
import { ChatComponent } from './chat/chat.component';

import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: 'full'},
  { path: "login", component: LoginComponent },
  { path: "user", component: UserComponent},
  { path: "admin", canActivate: [AdminGuard], component: AdminComponent},
  { path: "chat", component: ChatComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
