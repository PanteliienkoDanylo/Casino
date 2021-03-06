"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var transactions_routes_1 = require("./transactions.routes");
var transactions_component_1 = require("./transactions.component");
var transaction_service_1 = require("./transaction.service");
var TransactionsModule = (function () {
    function TransactionsModule() {
    }
    return TransactionsModule;
}());
TransactionsModule = __decorate([
    core_1.NgModule({
        imports: [
            common_1.CommonModule,
            transactions_routes_1.TransactionsRoutingModule,
            forms_1.FormsModule,
            http_1.HttpModule
        ],
        declarations: [
            transactions_component_1.TransactionsComponent
        ],
        providers: [transaction_service_1.TransactionService]
    })
], TransactionsModule);
exports.TransactionsModule = TransactionsModule;
//# sourceMappingURL=transactions.module.js.map