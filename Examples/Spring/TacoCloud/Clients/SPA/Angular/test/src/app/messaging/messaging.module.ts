import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessagesComponent } from './messages/messages.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    MessagesComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    MessagesComponent
  ]
})
export class MessagingModule { }
