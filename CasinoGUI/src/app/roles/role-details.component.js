"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
/**
 * Created by Вова on 20.03.2017.
 */
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var role_service_1 = require("./role.service");
var RoleDetailsComponent = (function () {
    function RoleDetailsComponent(roleService, route, router) {
        this.roleService = roleService;
        this.route = route;
        this.router = router;
    }
    RoleDetailsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            var id = Number.parseInt(params['id']);
            console.log('getting role with id: ', id);
            _this.roleService
                .get(id)
                .subscribe(function (r) { return _this.role = r; });
        });
    };
    RoleDetailsComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    RoleDetailsComponent.prototype.gotoRolesList = function () {
        var link = ['admin/roles'];
        this.router.navigate(link);
    };
    RoleDetailsComponent.prototype.updateRoleDetails = function () {
        this.roleService
            .update(this.role)
            .subscribe(function () { console.log('success'); });
    };
    RoleDetailsComponent = __decorate([
        core_1.Component({
            selector: 'role-details',
            template: "<!-- new syntax for ng-if -->\n    <section *ngIf=\"role\">\n        <section>\n            <h2>You selected: {{role.name}}</h2>\n<h3>Description</h3>\n<p>\n    {{role.description}} \n</p>\n</section>\n<section>\n    <form (ngSubmit)=\"updateRoleDetails()\" #roleForm=\"ngForm\">\n    <div>\n        <label for=\"name\">Name: </label>\n<input type=\"text\" name=\"name\" required [(ngModel)]=\"role.name\" #name=\"ngModel\">\n<div [hidden]=\"name.valid || name.pristine\" class=\"error\">\n    Name is required my good sir/lady!\n</div>\n</div>\n<div>\n    <label for=\"description\">Description: </label>\n<input type=\"text\" name=\"description\" [(ngModel)]=\"role.description\">\n    </div>\n    \n\n\n<button type=\"submit\" [disabled]=\"!roleForm.form.valid\">Update</button>\n</form>\n</section>\n\n<button (click)=\"gotoRolesList()\">Back to roles list</button>\n</section>\n    "
        }), 
        __metadata('design:paramtypes', [role_service_1.RoleService, router_1.ActivatedRoute, router_1.Router])
    ], RoleDetailsComponent);
    return RoleDetailsComponent;
}());
exports.RoleDetailsComponent = RoleDetailsComponent;
//# sourceMappingURL=role-details.component.js.map