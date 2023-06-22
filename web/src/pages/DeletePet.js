import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";

class DeletePet extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount'], this)
        this.client = new PawDorableClient();
    }

    mount(){}




}

const main = async () => {
    const page = new DeletePet();
    page.mount();
};

window.addEventListener('DOMContentLoaded', main);