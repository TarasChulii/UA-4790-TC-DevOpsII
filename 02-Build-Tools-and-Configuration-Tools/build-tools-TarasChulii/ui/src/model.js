export class RequestSender {
    constructor() {
    }
    sendJSONRequest() {
        return fetch(`${window.location.origin}/dogs`).then((response) => response.json())
    }
}