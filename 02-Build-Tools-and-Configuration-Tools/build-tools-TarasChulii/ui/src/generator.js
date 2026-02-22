import { createElement } from "./view";

class BaseComponent {
    constructor(object) {
        this.name = object.title
        this.age = object.age
        this.description = object.description
        this.gender = object.sex
        this.link = object.dogImage
        this._element = null;
    }

    getHTML() {

        return ``
    }

    getElement() {
        if (!this._element) {
            this._element = createElement(this.getHTML());
        }

        return this._element;
    }

    removeElement() {
        this._element = null;
    }
}

export class DogCardComponent extends BaseComponent {
    constructor(object) {
        super(object)
    }

    getHTML() {

        return `<div class="container">
        <img class="container-dog-picture" src="${this.link}">
        <div class="container-dog-info">
            <span class="text-base dog-info-name">${this.name}</span>
            <span class="text-base dog-info-gender">${this.gender}</span>
        </div>
    </div>`
    }
}
