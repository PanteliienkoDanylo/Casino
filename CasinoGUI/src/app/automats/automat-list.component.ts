
import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { AutomatService }  from './automat.service';
import {Automat} from "./automat";

@Component({
  template: `
    <h2>AUTOMATS</h2>
    <ul class="items">
      <li *ngFor="let automat of automats | async"
        [class.selected]="isSelected(automat)"
        (click)="onSelect(automat)">
        <span class="badge">{{ automat.id }}</span> {{ automat.name }}
      </li>
    </ul>

  `
})
export class AutomatListComponent implements OnInit {
  automats: Observable<Automat[]>;

  private selectedId: number;

  constructor(
    private service: AutomatService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.automats = this.route.params
      .switchMap((params: Params) => {
        this.selectedId = +params['id'];
        return this.service.getAutomats();
      });
  }

  isSelected(automat: Automat) { return automat.id === this.selectedId; }

  onSelect(automat: Automat) {
    this.router.navigate(['/automats', automat.id]);
  }
}
