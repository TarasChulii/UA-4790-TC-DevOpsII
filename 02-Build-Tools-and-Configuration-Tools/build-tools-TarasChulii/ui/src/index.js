import { DogCardComponent } from "./generator";
import { RequestSender } from "./model";
import { View } from "./view";

class AppController {
    constructor() {
        this.dogControllers = [];
    }
    init() {
        let sender = new RequestSender();
        sender.sendJSONRequest().then((data) => {
            data.forEach(element => {
                let dog = new DogController(element);
                dog.showDog();
                this.dogControllers.push(dog);
            });
        });
    }
}

class DogController {
    constructor(dogObject) {
        this.dog = dogObject;
        this.component = null;
        this.view = new View();
    }

    showDog() {
        this.component = new DogCardComponent(this.dog);
        this.view.render(this.component, ".list", "BEFOREEND");
    }
}

let app = new AppController()
app.init()