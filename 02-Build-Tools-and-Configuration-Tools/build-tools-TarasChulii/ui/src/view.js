export function createElement(template) {
    const newElement = document.createElement(`div`);
    newElement.innerHTML = template;

    return newElement.firstChild;
};

export const remove = (component) => {
    component.getElement().remove();
};

export class View {

    render(element, containerName, place) {

        let compomnent = element.getElement();

        let container = document.querySelector(containerName);

        switch (place) {
            case "AFTERBEGIN":
                container.prepend(compomnent);
                break;
            case "BEFOREEND":
                container.append(compomnent);
                break;
        }
    }
}